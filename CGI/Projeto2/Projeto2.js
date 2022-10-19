var gl;
var program;

//html
var mModelView; //mModel vai ser a mat4() e nao a vou alterar junto
var mModelViewLoc;
var mProjectionLoc;
var mProjection;

//objetos
var object;

//superquadric
var e1, e2;

//para a projecao axonometrica
var projectionAxo;
var theta = 30;
var gamma = 30;
var A = 42;
var B = 7;
var thetaRadians;
var gammaRadians;

//para a projecao obliqua
var mObliquo;
var projetionObli;
var l = 0.5; //update slider
var alpha = 45;

//para a projecao ortogonal
var mOrtogonal;
var projectionOrto;

//para a projecao perspetiva
var mPerspetiva;
var d = 12.25;

//dimensoes janela/visor
var width;
var height;

//para pintar
var filled = false;

//remocao de superficies
var ativarZ = false;
var ativarBack = false;

//zoom
var zoom = 1;
var zoomBunny = 5;

var s;

//objectos
var projectionAxoObjectLivre = { valueTheta: theta, valueGamma: gamma };
var projectionObliObjectLivre = { valueL: l, valueAlpha: alpha };
var projectionPerspetiva = { ValueD: d };
var superquadricValues = { valueE1: e1, valueE2: e2 };


window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = WebGLUtils.setupWebGL(canvas);
    if (!gl) { alert("WebGL isn't available"); }

    height = window.innerHeight;
    width = window.innerWidth;
    s = Math.min(width, height);
    canvas.width = width - 15;
    canvas.height = height / 1.5;
    gl.viewport((canvas.width - s) / 2, (canvas.height - s) / 2, s, s);
	
	
    gl.clearColor(0.0, 0.0, 0.0, 1.3);

    hideStuff();
    document.getElementById("e1").style.display = "none";
    document.getElementById("e2").style.display = "none";
    document.getElementById("valueE1").style.display = "none";
    document.getElementById("valueE2").style.display = "none";

    canvas.onwheel = function (e) {
        if (object != "Bunny") {
            if (zoom + e.deltaY / 500 > 0) {
                zoom += e.deltaY / 500;
                mProjection = ortho(-1.5 / zoom, 1.5 / zoom, -1.5 / zoom, 1.5 / zoom, -10, 10);
            }
        }
        else {
            if (zoomBunny + e.deltaY / 500 > 0) {
                zoomBunny += e.deltaY / 500;
                mProjection = ortho(-1.5 / zoomBunny, 1.5 / zoomBunny, -1.5 / zoomBunny, 1.5 / zoomBunny, -10, 10);
            }
        }
    }

    window.onresize = function () {
        height = window.innerHeight;
        width = window.innerWidth;
        s = Math.min(width, height);
        canvas.width = width - 15;
        canvas.height = height / 1.5;
        gl.viewport((canvas.width - s) / 2, (canvas.height - s) / 2, s, s);
        gl.clearColor(0.0, 0.0, 0.0, 0.4);
    }

    //objectos
    cubeInit(gl);
    sphereInit(gl);
    bunnyInit(gl);
    cylinderInit(gl);
    torusInit(gl);
    superquadricInit(gl);

    // Load shaders and initialize attribute buffers
    program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    mProjection = ortho(-1.5 / zoom, 1.5 / zoom, -1.5 / zoom, 1.5 / zoom, -10, 10);


    mModelViewLoc = gl.getUniformLocation(program, "mModelView");
    mProjectionLoc = gl.getUniformLocation(program, "mProjection");
    //inicial
    object = "Cube";
    projectionAxo = "Dimetrica";
    fProjectionAxo();

    callback();

    render();
}

function hideStuff() {
    document.getElementById("Cavaleira").style.display = "none";
    document.getElementById("Gabinete").style.display = "none";
    document.getElementById("ObliLivre").style.display = "none";
    document.getElementById("Isometrica").style.display = "block";
    document.getElementById("Trimetria").style.display = "block";
    document.getElementById("Dimetrica").style.display = "block";
    document.getElementById("AxoLivre").style.display = "block";
    document.getElementById("AlcadoPrin").style.display = "none";
    document.getElementById("Planta").style.display = "none";
    document.getElementById("LateralD").style.display = "none";
    document.getElementById("theta").style.display = "none";
    document.getElementById("gamma").style.display = "none";
    //document.getElementById("e1").style.display = "none";
    //document.getElementById("e2").style.display = "none";
    document.getElementById("l").style.display = "none";
    document.getElementById("alpha").style.display = "none";
    document.getElementById("d").style.display = "none";
    //document.getElementById("valueE1").style.display = "none";
    //document.getElementById("valueE2").style.display = "none";
    document.getElementById("valueD").style.display = "none";
    document.getElementById("valueL").style.display = "none";
    document.getElementById("valueAlpha").style.display = "none";
    document.getElementById("thetaAxo").style.display = "none";
    document.getElementById("gammaAxo").style.display = "none";
}


function callback() {

    //escolher modo de visualizacao dps de escolher o objecto, z bufer e back face culling
    document.body.onkeyup = function (e) {
        if (e.which == 87 || e.which == 119) { //letra W  para malha
            filled = false;
        }
        if (e.which == 102 || e.which == 70) {// tecla F   para preencher
            filled = true;

        }

        if (e.which == 90 || e.which == 122) { //letra z -- z buffer//letra W  para malha

            if (!ativarZ) {
                ativarZ = true;
                gl.enable(gl.DEPTH_TEST);
            } else if (ativarZ) {
                ativarZ = false;
                gl.disable(gl.DEPTH_TEST);
            }
        }
        if (e.which == 66 || e.which == 98) {// tecla B  -- back face culling
            if (!ativarBack) {
                gl.enable(gl.CULL_FACE);
                ativarBack = true;
                //gl.frontFace(gl.BACK);
            }
            else if (ativarBack) {
                ativarBack = false;
                gl.disable(gl.CULL_FACE);
            }
        }
    };

    document.getElementById("e1").addEventListener("input", function (e) {
        superquadricValues.valueE1 = e.target.value;
    });

    document.getElementById("e2").addEventListener("input", function (e) {
        superquadricValues.valueE2 = e.target.value;
    });

    //valores a modificar pelo user
    document.getElementById("theta").addEventListener("input", function (e) {
        projectionAxoObjectLivre.valueTheta = e.target.value;
        console.log("thet " + projectionAxoObjectLivre.valueTheta);
        fProjectionAxo();
    });

    document.getElementById("gamma").addEventListener("input", function (e) {
        projectionAxoObjectLivre.valueGamma = e.target.value;
        console.log("gamma " + projectionAxoObjectLivre.valueGamma)
        fProjectionAxo();
    });

    document.getElementById("l").addEventListener("input", function (e) {
        projectionObliObjectLivre.valueL = e.target.value;
        fProjectionObliqua();
    });

    document.getElementById("alpha").addEventListener("input", function (e) {
        projectionObliObjectLivre.valueAlpha = e.target.value;
        console.log("alpha " + projectionObliObjectLivre.valueAlpha);
        fProjectionObliqua();
    });



    document.getElementById("d").addEventListener("input", function (e) {
        projectionPerspetiva.ValueD = e.target.value;
        fProjectionPerspetive();
    });

    function handleChoices(value) {
        hideStuff();
        document.getElementById("Dimetrica").style.display = "none";
        document.getElementById("Isometrica").style.display = "none";
        document.getElementById("Trimetria").style.display = "none";
        document.getElementById("AxoLivre").style.display = "none";

        switch (value) {
            case "Axonometrica":
            case "Isometrica":
            case "Dimetrica":
            case "Trimetria":
                document.getElementById("Isometrica").style.display = "block";
                document.getElementById("Trimetria").style.display = "block";
                document.getElementById("Dimetrica").style.display = "block";
                document.getElementById("AxoLivre").style.display = "block";
                break;
            case "AxoLivre":
                document.getElementById("Isometrica").style.display = "block";
                document.getElementById("Trimetria").style.display = "block";
                document.getElementById("Dimetrica").style.display = "block";
                document.getElementById("AxoLivre").style.display = "block";
                document.getElementById("thetaAxo").style.display = "block";
                document.getElementById("theta").style.display = "block";
                document.getElementById("gammaAxo").style.display = "block";
                document.getElementById("gamma").style.display = "block";
                break;
            case "Ortogonal":
            case "AlcadoPrin":
            case "Planta":
            case "LateralD":
                document.getElementById("AlcadoPrin").style.display = "block";
                document.getElementById("Planta").style.display = "block";
                document.getElementById("LateralD").style.display = "block";
                break;
            case "Obliqua":
            case "Cavaleira":
            case "Gabinete":
                document.getElementById("Cavaleira").style.display = "block";
                document.getElementById("Gabinete").style.display = "block";
                document.getElementById("ObliLivre").style.display = "block";
                break;
            case "ObliLivre":
                document.getElementById("Cavaleira").style.display = "block";
                document.getElementById("Gabinete").style.display = "block";
                document.getElementById("ObliLivre").style.display = "block";
                document.getElementById("valueL").style.display = "block";
                document.getElementById("l").style.display = "block";
                document.getElementById("valueAlpha").style.display = "block";
                document.getElementById("alpha").style.display = "block";
                break;
            case "Perspetiva":
                document.getElementById("valueD").style.display = "block";
                document.getElementById("d").style.display = "block";
                fProjectionPerspetive();
                break;
            default:
        }
    }


    document.getElementById("shapes").addEventListener("change", function (e) {
        document.getElementById("valueE1").style.display = "none";
        document.getElementById("valueE2").style.display = "none";
        document.getElementById("e1").style.display = "none";
        document.getElementById("e2").style.display = "none";
        switch (this.value) {
            case "Cube":
                object = "Cube";
                mProjection = ortho(-1.5 / zoom, 1.5 / zoom, -1.5 / zoom, 1.5 / zoom, -10, 10);
                break;
            case "Sphere":
                object = "Sphere";
                mProjection = ortho(-1.5 / zoom, 1.5 / zoom, -1.5 / zoom, 1.5 / zoom, -10, 10);
                break;
            case "Cylinder":
                object = "Cylinder";
                mProjection = ortho(-1.5 / zoom, 1.5 / zoom, -1.5 / zoom, 1.5 / zoom, -10, 10);
                break;
            case "Torus":
                object = "Torus";
                mProjection = ortho(-1.5 / zoom, 1.5 / zoom, -1.5 / zoom, 1.5 / zoom, -10, 10);
                break;
            case "Bunny":
                object = "Bunny";
                mProjection = ortho(-1.5 / zoomBunny, 1.5 / zoomBunny, -1.5 / zoomBunny, 1.5 / zoomBunny, -10, 10);
                break;
            case "Superquadric":
                object = "Superquadric";
                mProjection = ortho(-1.5 / zoom, 1.5 / zoom, -1.5 / zoom, 1.5 / zoom, -10, 10);
                document.getElementById("valueE1").style.display = "block";
                document.getElementById("valueE2").style.display = "block";
                document.getElementById("e1").style.display = "block";
                document.getElementById("e2").style.display = "block";
        }
    });

    document.getElementById("projections").addEventListener("change", function (e) {
        switch (this.value) {
            case "Axonometrica":
                handleChoices(this.value);
                break;
            case "Ortogonal":
                handleChoices(this.value);
                break;
            case "Obliqua":
                handleChoices(this.value);
                break;
            case "Perspetiva":
                handleChoices(this.value);
                break;
        }
    });

    //Projecoes
    //projecao ortogonal
    document.getElementById("AlcadoPrin").addEventListener("click", function (e) {
        projectionOrto = "AlcadoPrin";
        handleChoices("AlcadoPrin");
        calculoProjectionOrto();

    });

    document.getElementById("Planta").addEventListener("click", function (e) {
        projectionOrto = "Planta";
        handleChoices("Planta");
        calculoProjectionOrto();


    });

    document.getElementById("LateralD").addEventListener("click", function (e) {
        projectionOrto = "LateralD";
        handleChoices("LateralD");
        calculoProjectionOrto();



    });

    //projecao Axonometrica
    document.getElementById("Isometrica").addEventListener("click", function (e) {
        projectionAxo = "Isometrica";
        handleChoices("Isometrica");
        fProjectionAxo();



    });

    document.getElementById("Dimetrica").addEventListener("click", function (e) {
        projectionAxo = "Dimetrica";
        handleChoices("Dimetrica");
        fProjectionAxo();



    });

    document.getElementById("Trimetria").addEventListener("click", function (e) {
        projectionAxo = "Trimetria";
        handleChoices("Trimetria");
        fProjectionAxo();




    });

    document.getElementById("AxoLivre").addEventListener("click", function (e) {
        projectionAxo = "AxoLivre";
        handleChoices("AxoLivre");
        fProjectionAxo();


    });

    //projecao obliqua
    document.getElementById("Cavaleira").addEventListener("click", function (e) {
        projetionObli = "Cavaleira";
        handleChoices("Cavaleira");
        fProjectionObliqua();



    });

    document.getElementById("Gabinete").addEventListener("click", function (e) {
        projetionObli = "Gabinete";
        handleChoices("Gabinete");
        fProjectionObliqua();



    });

    document.getElementById("ObliLivre").addEventListener("click", function (e) {
        projetionObli = "ObliLivre";
        handleChoices("ObliLivre");
        fProjectionObliqua();


    });

    //projecao perspetiva
    document.getElementById("Perspetiva").addEventListener("click", function (e) {
        handleChoices("Perspetiva");
        fProjectionPerspetive();



    });

}

function fProjectionPerspetive() {
    d = projectionPerspetiva.ValueD;
    mPerspetiva = mat4(1, 0, 0, 0,
        0, 1, 0, 0,
        0, 0, 1, d,
        0, 0, 0, 1);
    console.log("PERSP" + mPerspetiva);
    mModelView = mPerspetiva;

}

function calculoProjectionOrto() {
    switch (projectionOrto) {
        case "AlcadoPrin":
            // plano z = 0;
            mOrtogonal = mat4();
            console.log(mOrtogonal);
            break;
        case "Planta":
            // plano z = 0;
            mOrtogonal = mult(rotateX(90), mat4(1, 0, 0, 0,
                0, 0, -1, 0,
                0, 0, 1, 0,
                0, 0, 0, 1));

            break;
        case "LateralD":
            // plano z = 0;
            //90 porque queremos rodar para a direita
            mOrtogonal = mult(rotateY(-90), mat4(1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1));

            break;
    }
    mModelView = mOrtogonal;
}

function degrees(radians) {
    return radians * 180 / Math.PI;
}

function calculoProjAxo() {
    thetaRadians = Math.atan(Math.sqrt(Math.tan(radians(A)) / Math.tan(radians(B)))) - (Math.PI / 2);
    gammaRadians = Math.asin(Math.sqrt(Math.tan(radians(A)) * Math.tan(radians(B))));

    theta = degrees(thetaRadians);
    gamma = degrees(gammaRadians);

}

function fProjectionAxo() {
    switch (projectionAxo) {
        case "Isometrica":
            A = 30;
            B = 30;
            calculoProjAxo();
            break;
        case "Dimetrica":
            A = 42;
            B = 7;
            calculoProjAxo();
            break;
        case "Trimetria":
            A = 54.26;
            B = 23.26;
            calculoProjAxo();
            break;
        case "AxoLivre":
            theta = projectionAxoObjectLivre.valueTheta;
            gamma = projectionAxoObjectLivre.valueGamma;
            break;
    }
    mModelView = mult(rotateX(gamma), rotateY(theta));

}

function calculoProjectionObliqua() {
    mObliquo = mat4(1, 0, -l * Math.cos(radians(alpha)), 0,
        0, 1, -l * Math.sin(radians(alpha)), 0,
        0, 0, 1, 0,
        0, 0, 0, 1);
}

function fProjectionObliqua() {
    switch (projetionObli) {
        case "Cavaleira":
            l = 1;
            calculoProjectionObliqua();
            break;
        case "Gabinete":
            l = 0.5;
            calculoProjectionObliqua();
            break;
        case "ObliLivre":
            l = projectionObliObjectLivre.valueL;
            alpha = projectionObliObjectLivre.valueAlpha;
            calculoProjectionObliqua();
            break;
    }
    mModelView = mObliquo;

}

//escolha do objeto que inicialmente vai ter a forma de arame
function chooseObject() {
    switch (object) {
        case "Cube":
            cubeDraw(gl, program, filled);
            break;
        case "Sphere":
            sphereDraw(gl, program, filled);
            break;
        case "Cylinder":
            cylinderDraw(gl, program, filled);
            break;
        case "Bunny":
            bunnyDraw(gl, program, filled);
            break;
        case "Torus":
            torusDraw(gl, program, filled);
            break;
        case "Superquadric":
            superquadricInit(gl, superquadricValues.valueE1, superquadricValues.valueE2);
            superquadricDraw(gl, program, filled);
            break;
    }
}

function render() {
    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    gl.uniformMatrix4fv(mProjectionLoc, false, flatten(mProjection));
    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(mModelView));
    chooseObject();
    requestAnimFrame(render);

}

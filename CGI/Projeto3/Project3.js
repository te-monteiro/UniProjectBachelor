//var canvas;
var gl;
var program;
var programTextura;

var aspect;

var mProjectionLoc, mModelViewLoc;

var matrixStack = [];
var modelView;
var view;

//objecto preenchido ou nao
var filled = false;

//lookAt    
var eye = [0, 1.5, 3.2];
var at = [0, 1, 0];
var up = [0, 1, 0];

var projecao = "Perspetiva";
var projection;


//cores
var color;

//eixos aviao
var pitch = 0;
var yaw = 0;
var roll = 0;

//rotacao das helices
var rotacao = 0;

//velocidade de rotacao que a helice vai ter 
var velocidadeDeRotacao = 0;

//velocidade do aviao
var velocidade = 0;

//posicao do aviao atual
var posicaoAtual = [0, 0, 0];

//direcao currente do aviao
var dir = 90;
//vetor de direcao currente do aviao
var v = [1, 1, 1];

//texturas
var texture;

// Stack related operations
function pushMatrix() {
    var m = mat4(modelView[0], modelView[1],
        modelView[2], modelView[3]);
    matrixStack.push(m);
}
function popMatrix() {
    modelView = matrixStack.pop();
}
// Append transformations to modelView
function multMatrix(m) {
    modelView = mult(modelView, m);
}
function multTranslation(t) {
    modelView = mult(modelView, translate(t));
}
function multScale(s) {
    modelView = mult(modelView, scalem(s));
}
function multRotationX(angle) {
    modelView = mult(modelView, rotateX(angle));
}
function multRotationY(angle) {
    modelView = mult(modelView, rotateY(angle));
}
function multRotationZ(angle) {
    modelView = mult(modelView, rotateZ(angle));
}

function fit_canvas_to_window() {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    aspect = canvas.width / canvas.height;
    gl.viewport(0, 0, canvas.width, canvas.height);

}

window.onresize = function () {
    fit_canvas_to_window();
}

window.onload = function () {
    canvas = document.getElementById('gl-canvas');

    gl = WebGLUtils.setupWebGL(document.getElementById('gl-canvas'));
    fit_canvas_to_window();

    gl.clearColor(0.0, 0.0, 0.0, 0.7);

    gl.enable(gl.DEPTH_TEST);

    program = initShaders(gl, 'default-vertex', 'default-fragment');
    programTextura = initShaders(gl, 'texturaVertex-shader', 'texturaFragment-shader');
    gl.useProgram(programTextura);
    setupTexture();
    gl.useProgram(program);


    mModelViewLoc = gl.getUniformLocation(program, "mModelView");
    mProjectionLoc = gl.getUniformLocation(program, "mProjection");

    sphereInit(gl);
    cubeInit(gl);
    cylinderInit(gl);
    pyramidInit(gl);

    projecoes();

    callback();

    render();
}

function setupTexture() {
    // Create a texture.
    texture = gl.createTexture();
    gl.bindTexture(gl.TEXTURE_2D, texture);
    // Fill the texture with a 1x1 blue pixel.
    gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGBA, 1, 1, 0, gl.RGBA, gl.UNSIGNED_BYTE,
        new Uint8Array([0, 0, 255, 255]));
    // Asynchronously load an image
    var image = new Image();
    image.src = "textura.png";
    image.onload = function () {
        gl.bindTexture(gl.TEXTURE_2D, texture);
        gl.pixelStorei(gl.UNPACK_FLIP_Y_WEBGL, true);
        gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.REPEAT);
        gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.REPEAT);
        gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
        gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR);
        gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGBA, gl.RGBA, gl.UNSIGNED_BYTE, image);
        gl.bindTexture(gl.TEXTURE_2D, null);
    };
}


function callback() {

    document.body.onkeyup = function (e) {

        //pinta ou nao o aviao
        if (e.which == 79) { //letra O
            if (filled == false)
                filled = true;
            else
                filled = false;
        }

        if (e.which == 48) { //vista perspetiva
            projecao = "Perspetiva";
            projecoes();
        }
        //vista topo 1
        if (e.which == 49) {
            projecao = "Topo";
            projecoes();
        }

        if (e.which == 50) { //vista lateral direito 2
            projecao = "Frente";
            projecoes();
        }

        if (e.which == 51) { //vista frente 3
            projecao = "LateralD";
            projecoes();
        }

        if (e.which == 87) { //letra W e S
            pitch += 5;
            pitch = Math.min(pitch, 45);
            // dir -= pitch;
        } else if (e.which == 83) {
            pitch -= 5;
            pitch = Math.max(pitch, - 45);
            //dir += pitch;
        }

        if (e.which == 69) { //letra E  
            yaw += 1;
            yaw = Math.min(yaw, 45);
            dir += 1;
        }
        else if (e.which == 81) {//letra Q
            yaw -= 1;
            yaw = Math.max(yaw, -45);
            dir -= 1;
        }

        if (e.which == 82) { //letra R 
            velocidadeDeRotacao += 0.5;
            velocidadeDeRotacao = Math.min(velocidadeDeRotacao, 200);

        } else if (e.which == 70) { // letra  F
            velocidadeDeRotacao -= 0.5;
            velocidadeDeRotacao = Math.max(velocidadeDeRotacao, 0);
        }

        if (e.which == 65) { //letra A e D
            roll += 5;
            roll = Math.min(roll, 45);
            //dir -= roll;

        } else if (e.which == 68) {
            roll -= 5;
            roll = Math.max(roll, -45);
            // dir += roll;
        }
    }
}

function projecoes() {
    switch (projecao) {

        case "Frente":
            projection = ortho(-10, 10, -10, 10, -10, 10);

            eye = add([1, 1, 0], posicaoAtual);
            at = add([1, 1, 0], posicaoAtual);
            up = [0, 1, 0];
            break;
        case "Topo":
            projection = ortho(-10, 10, -10, 10, -10, 10);

            eye = add([0, 1, 1], posicaoAtual);
            at = add([0, 0, 1], posicaoAtual);
            up = [0, -1, -1];
            break;
        case "LateralD":
            projection = ortho(-10, 10, -10, 10, -10, 10);

            eye = add([1, 0, 1], posicaoAtual);
            at = add([0, 0, 1], posicaoAtual);
            up = [0, 1, 0];
            break;
        case "Perspetiva":
            projection = perspective(120, 0.7, 0.1, 10);

            eye = add([0, 1.5, 3.2], posicaoAtual);
            at = add([0, 1, 1], posicaoAtual);
            up = [0, 1, 0];
            break;

    }
    modelView = lookAt(eye, at, up);
}

function corpoA() {

    multScale([0.4, 5.0, 0.5]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    gl.uniform3fv(color, vec3(1.0, 0.0, 0.0));
    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);
    sphereDraw(gl, program, filled);

}

function asasFrente() {
    multTranslation([0.0, 1.1, 0.0]);
    multScale([0.1, 1.0, 3.9]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [0.8, 0.8, 0.8]);

    pyramidDraw(gl, program, filled);

}

function asasTras() {
    multTranslation([0.0, -1.9, 0.0]);
    multScale([0.1, 1.0, 1.2]);
    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));

    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [0.8, 0.8, 0.8]);

    pyramidDraw(gl, program, filled);

}

function asasCima() {

    multTranslation([0.15, -2.0, 0.0]);

    multRotationY(-90);
    multRotationX(5);
    multScale([0.1, 0.7, 0.3]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [0.8, 0.8, 0.8]);

    pyramidDraw(gl, program, filled);

}

function flapsCima() {
    multTranslation([0.2, -2.357, 0.0]);

    multRotationY(-90);
    multRotationZ(yaw);
    multScale([0.02, 0.09, 0.15]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);
    cubeDraw(gl, program, filled);

}

function flapsDir() {
    multTranslation([0.0, 0.6, -1.12]);
    multRotationZ(roll);

    multScale([0.02, 0.1, 1.5]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);
    cubeDraw(gl, program, filled);

}

function flapsEsq() {

    multTranslation([0.0, 0.6, 1.12]);
    multRotationZ(roll);

    multScale([0.02, 0.1, 1.5]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);
    cubeDraw(gl, program, filled);

}
function flapsDirAtras() {
    multTranslation([0.0, -2.4, -0.3]);
    multRotationZ(pitch);

    multScale([0.02, 0.1, 0.25]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);
    cubeDraw(gl, program, filled);

}

function flapsEsqAtras() {

    multTranslation([0.0, -2.4, 0.3]);
    multRotationZ(pitch);

    multScale([0.02, 0.1, 0.25]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);
    cubeDraw(gl, program, filled);

}

function helice1() {
    multScale([0.05, 0.05, 0.6]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [0.8, 0.8, 0.8]);
    cubeDraw(gl, program, filled);
}

function helice2() {
    multRotationY(60);

    multScale([0.05, 0.05, 0.6]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [0.8, 0.8, 0.8]);
    cubeDraw(gl, program, filled);
}

function helice3() {


    multRotationY(-60);
    multScale([0.05, 0.05, 0.6]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [0.8, 0.8, 0.8]);

    cubeDraw(gl, program, filled);
}

function rodaDir() {
    multTranslation([-0.4, 0.8, 0.07]);
    multRotationX(90);
    multScale([0.05, 0.03, 0.01]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));

    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);

    cylinderDraw(gl, program, filled);
}

function rodaEsq() {
    multTranslation([-0.4, 0.8, -0.07]);
    multRotationY(90);
    multScale([0.05, 0.03, 0.1]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));

    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);

    cylinderDraw(gl, program, filled);
}


function rodaFrente() {
    multTranslation([-0.4, 1.9, 0.0]);
    multRotationY(90);
    multScale([0.05, 0.03, 0.1]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));

    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);

    cylinderDraw(gl, program, filled);
}

//ligacao da roda esquerda com o aviao
function ligacaoEsq() {
    multTranslation([-0.3, 0.8, -0.07]);
    multRotationY(90);
    multScale([0.02, 0.02, 0.2]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));

    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);

    cubeDraw(gl, program, filled);
}

function ligacaoDir() {
    multTranslation([-0.3, 0.8, 0.07]);
    multRotationY(90);
    multScale([0.02, 0.02, 0.2]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));

    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);

    cubeDraw(gl, program, filled);
}
function ligacaoFrente() {
    multTranslation([-0.3, 1.9, 0.0]);
    multRotationY(90);
    multScale([0.02, 0.02, 0.3]);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));

    color = gl.getUniformLocation(program, "color");
    gl.uniform3fv(color, [1.0, 1.0, 1.0]);

    cubeDraw(gl, program, filled);
}
var aresta = 6;

function solo() {
    gl.useProgram(programTextura);
    pushMatrix();
    gl.activeTexture(gl.TEXTURE0);
    gl.bindTexture(gl.TEXTURE_2D, texture);
    gl.uniform1i(gl.getUniformLocation(programTextura, "texture"), 0);
    gl.uniformMatrix4fv(gl.getUniformLocation(programTextura, "mProjection"), false, flatten(projection));
    for (var horizontal = -3 + posicaoAtual[2]; horizontal < 3 + posicaoAtual[2]; horizontal++)
        for (var vertical = -3 + posicaoAtual[0]; vertical < 3 + + posicaoAtual[0]; vertical++) {
            pushMatrix();
            multTranslation([vertical, -0.5, horizontal]);
            multScale([aresta, 0.1, aresta]);
            gl.uniformMatrix4fv(gl.getUniformLocation(programTextura, "mModelView"), false, flatten(modelView));
            cubeDraw(gl, programTextura, true);
            popMatrix();
        }
    popMatrix();
    gl.useProgram(program);
}


function aviao() {
    pushMatrix();

    //aviao a andar
    multTranslation(posicaoAtual);

    //movimentos direcionais do aviao
    // multRotationX(pitch);
    multRotationY(yaw);
    //multRotationZ(roll);

    multRotationY(-90);
    multRotationZ(90);

    pushMatrix();
    corpoA();
    popMatrix();

    pushMatrix();
    asasFrente();
    popMatrix();

    pushMatrix();
    asasTras();
    popMatrix();

    pushMatrix();
    asasCima();
    popMatrix();

    pushMatrix();
    flapsCima();
    popMatrix();

    pushMatrix();
    flapsDir();
    popMatrix();

    pushMatrix();
    flapsEsq();
    popMatrix();

    pushMatrix();
    flapsDirAtras();
    popMatrix();

    pushMatrix();
    flapsEsqAtras();
    popMatrix();

    pushMatrix();
    //translacao helices

    multTranslation([0.0, 2.5, 0.0]);
    multRotationY(rotacao);

    pushMatrix();
    helice1();
    popMatrix();

    pushMatrix();
    helice2();
    popMatrix();

    pushMatrix();
    helice3();
    popMatrix();
    popMatrix();

    pushMatrix();
    rodaDir();
    popMatrix();

    pushMatrix();
    rodaEsq();
    popMatrix();

    pushMatrix();
    rodaFrente();
    popMatrix();

    pushMatrix();
    ligacaoEsq();
    popMatrix();

    pushMatrix();
    ligacaoDir();
    popMatrix();

    pushMatrix();
    ligacaoFrente();
    popMatrix();


    popMatrix();

}

function render() {
    requestAnimationFrame(render);

    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    //projecoes e vistas
    modelView = projection;
    projecoes();
    gl.uniformMatrix4fv(mProjectionLoc, false, flatten(projection));

    //calculo da velocidade do aviao, o 1000 serve para a velocidade nao ser enorme
    velocidade = velocidadeDeRotacao / 1000;
    //calculo da velocidade de rotacao das helices
    rotacao += velocidadeDeRotacao;

    //calculo das posicoes do aviao corrente
    //nota: os eixos sao o y para cima, o z e o x estao ao contrario do normal
    v = ([Math.cos(radians(dir)) * velocidade, 0, -Math.sin(radians(dir)) * velocidade]);


    posicaoAtual = add(posicaoAtual, v);

    pushMatrix();
    //desenho do chao 
    solo();
    popMatrix();

    //desenho do aviao
    aviao();

}
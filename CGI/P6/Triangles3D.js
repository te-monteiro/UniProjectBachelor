var gl;
var primitives = [];
var mModel_list;
var mView;
var mProjection;
var mModelLoc;
var mViewLoc;
var mProjectionLoc;
var tx = 0, ty = 0, tz = 0;
var rx = 0, ry = 0, rz = 0;    
var sx = 1, sy = 1, sz = 1;
var program;


window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = WebGLUtils.setupWebGL(canvas);
    if(!gl) { alert("WebGL isn't available"); }
    
     // alteracao das dimensões da janela podem ser acedidas
        height = window.innerHeight;
        width = window.innerWidth;
        var s = Math.min(width, height);
        canvas.width = s;
        canvas.height = s;
        gl.viewport(0,0,s,s);
        gl.clearColor(0.0, 0.0, 0.0, 1.0);
    
        if(!gl) { alert("WebGL isn't available"); }

    
    mModel_list = [];
    var at = [0, 0, 0];
    var eye = [0, 0, 1];
    var up = [0, 1, 0];
    
    mView = lookAt(eye, at, up);
    mProjection = ortho(-2,2,-2,2,10,-10);
    
    gl.enable(gl.DEPTH_TEST);
    
    // Configure WebGL
    gl.viewport(0,0,canvas.width, canvas.height);
    gl.clearColor(0.0, 0.0, 0.0, 1.0);
    
    // Load shaders and initialize attribute buffers
    program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);
        
      
    cubeInit(gl);
    sphereInit(gl);
    
    mModelLoc = gl.getUniformLocation(program, "mModel")
    mViewLoc = gl.getUniformLocation(program, "mView");
    mProjectionLoc = gl.getUniformLocation(program, "mProjection");
    
    gl.uniformMatrix4fv(mViewLoc, false, flatten(mView));
    gl.uniformMatrix4fv(mProjectionLoc, false, flatten(mProjection));
  
   
    //translacao
    document.getElementById("tx").addEventListener("input", function(e){
        tx = e.target.value
        ctm_calculate();
    });
    
    document.getElementById("ty").addEventListener("input", function(e){
        ty = e.target.value
        ctm_calculate();
    });
    document.getElementById("tz").addEventListener("input", function(e){
        tz = e.target.value
        ctm_calculate();
    });
    
    //rotacao
    document.getElementById("rx").addEventListener("input", function(e){
        rx = e.target.value
        ctm_calculate();
    });
    document.getElementById("ry").addEventListener("input", function(e){
        ry = e.target.value
        ctm_calculate();
    });
    document.getElementById("rz").addEventListener("input", function(e){
        rz = e.target.value
        ctm_calculate();
    });
    
    //escala
    document.getElementById("sx").addEventListener("input", function(e){
        sx = e.target.value
        ctm_calculate();
    });
    document.getElementById("sy").addEventListener("input", function(e){
        sy = e.target.value
        ctm_calculate();
    });
    document.getElementById("sz").addEventListener("input", function(e){
        sz = e.target.value
        ctm_calculate();
    });
    
    document.getElementById("guardar").addEventListener("click", function(e){
        mModel_list.push(mat4());
        reset();
    });
    
    document.getElementById("reset").addEventListener("click", function(e){
        reset();
    });
    
    document.getElementById("cube").addEventListener("click", function(e){
        push_primitive("cube");
});
    document.getElementById("esfera").addEventListener("click", function(e){
        push_primitive("esfera");
});
    render();
}

function ctm_calculate(){
    
    var t = translate(tx,ty,tz);
    var rotx = rotateX(rx); 
    var roty = rotateY(ry);
    var rotz = rotateZ(rz);
    var s = scalem(sx, sy,  sz);
    
    var rot = mult(rotx,mult(rotz, roty));
    
    mModel_list[mModel_list.length - 1] = mult(t, mult(rot, s));         
}

 function reset(){
       var tx = 0, ty = 0, tz = 0;
       var rx = 0, ry = 0, rz = 0;    
       var sx = 1, sy = 1, sz = 1;
}

function push_primitive(name)
{
    primitives.push(name);
    mModel_list.push(mat4());
    tx = 0;
    ty = 0;
    tz = 0;
    rx = 0;
    ry = 0;
    rz = 0;
    sx = 1;
    sy = 1;
    sz = 1;
    
    updateSliders();
}

function updateSliders(){
    document.getElementById("tx").value = tx;
    document.getElementById("ty").value = ty;
    document.getElementById("tz").value = tz;
    document.getElementById("rx").value = rx;
    document.getElementById("ry").value = ry;
    document.getElementById("rz").value = rz;
    document.getElementById("sx").value = sx;
    document.getElementById("sy").value = sy;
    document.getElementById("sz").value = sz;
}


function render() {
    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
    
    //mModel = mModel_list[i];
    gl.uniformMatrix4fv(mModelLoc, false, flatten(mView));
    cubeDrawWireFrame(gl, program);
    
   /** if(primitives.length != 0){
        for(i in primitives){
            
            mModel = mModel_list[i];
            gl.uniformMatrix4fv(mModelLoc, false, flatten(mModel));
            
            if(primitives[i] == "cube"){
                cubeDrawWireFrame(gl, program);
            } else if(primitives[i] == "esfera"){
                sphereDrawWireFrame(gl, program);
            }
        }
    }*/
            
    requestAnimFrame(render);
       
}
var gl;
var ctm_list;
//var ctm;
var ctmloc;
var sliderValueX = 0;
var sliderValueY= 0;
var slideValueRot= 0;
var slideValueEscalaX = 1;
var slideValueEscalaY = 1;


window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = WebGLUtils.setupWebGL(canvas);
    if(!gl) { alert("WebGL isn't available"); }
    
    // Three vertices
    var vertices = [
        vec2(-0.5,-0.5),
        vec2(0.5,-0.5),
        vec2(0,0.5)
    ];
    
   
    
    //ctm variavel que guarda a matriz que e iniciada pela matriz identidade
    //ctm = mat4();
    //uma lista de todas ctm 
    ctm_list = [mat4()];
   
    
    // Configure WebGL
    gl.viewport(0,0,canvas.width, canvas.height);
    gl.clearColor(0.0, 0.0, 0.0, 1.0);
    
    // Load shaders and initialize attribute buffers
    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    // Load the data into the GPU
    var bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(vertices), gl.STATIC_DRAW);

    // Associate our shader variables with our data buffer
    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, sizeof['vec2'], 0);
    gl.enableVertexAttribArray(vPosition);
    
    ctmLoc = gl.getUniformLocation(program, "ctm");
    //sempre o mesmo valor, tenho que passar um valor
    // e sempre falso por causa da regras de api webgl
    gl.uniformMatrix4fv(ctmLoc, false, flatten(ctm_list[ctm_list.length-1]));
    
    var deslX = document.getElementById("slideDX");
    
    var deslY = document.getElementById("slideDY");
    
    var rotZ = document.getElementById("slideRZ");
    
    var escalaX = document.getElementById("slideEX");
    
    var escalaY = document.getElementById("slideEY");
   //deslocamento em x
    
    deslX.addEventListener("input", function(e){
        sliderValueX = e.target.value;
        console.log(sliderValueX);
        ctm_calculate();    
    });
    
    //deslocamento em y
      
    deslY.addEventListener("input", function(e){
        sliderValueY = e.target.value;
        ctm_calculate();
                
    });
    
    //rotacao em z
    
    rotZ.addEventListener("input", function(e){
        slideValueRot = e.target.value;
        
        ctm_calculate();
    });
    
    //escala em x
    
    escalaX.addEventListener("input", function(e){
        slideValueEscalaX = e.target.value;
        
        ctm_calculate();
    });
    
    //escala em y
   
    escalaY.addEventListener("input", function(e){
        slideValueEscalaY = e.target.value;
        
        ctm_calculate();
    });
    
    document.getElementById("guardar").addEventListener("click", function(e){
        ctm_list.push(mat4());
        sliderValueX = 0;
        sliderValueY= 0;
        slideValueRot= 0;
        slideValueEscalaX = 1;
        slideValueEscalaY = 1;
        //ele guarda as info anteriores e atualiza para a pos inicial
            
    });
     //    faz o reset total
     document.getElementById("resetTotal").addEventListener("click", function(e){
        ctm_list = 0;
        ctm_list = mat4(); 
    
        sliderValueX = 0;
        sliderValueY= 0;
        slideValueRot= 0;
        slideValueEscalaX = 1;
        slideValueEscalaY = 1;
        
    });
      document.getElementById("reset").addEventListener("click", function(e){
        for(i in ctm_list){
            ctm_list[i] = 0;
           
       // ctm_list = mat4(); 
        ctm_list = mat4();
        sliderValueX = 0;
        sliderValueY= 0;
        slideValueRot= 0;
        slideValueEscalaX = 1;
        slideValueEscalaY = 1;
        }
    });
    
    render();
}

function ctm_calculate(){
    
    var t = translate(sliderValueX, sliderValueY, 1);
    console.log("sou o t : " +t); 
    var r = rotateZ(slideValueRot); //rotateZ
    console.log(slideValueRot);
    var s = scalem(slideValueEscalaX, slideValueEscalaY, 1);
    ctm_list[ctm_list.length-1] = mult(t, mult(r, s));
      
}

function render() {
    gl.clear(gl.COLOR_BUFFER_BIT);
    
    for(i in ctm_list){
        var ctm = ctm_list[i];
         
        gl.uniformMatrix4fv(ctmLoc, false, flatten(ctm));
        
        
        gl.drawArrays(gl.TRIANGLES, 0, 3);

    }
    requestAnimFrame(render);
       
}
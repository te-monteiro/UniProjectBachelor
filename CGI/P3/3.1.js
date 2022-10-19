var gl;
var startPosition;
var endPosition;
var isDrawing = true;


window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = WebGLUtils.setupWebGL(canvas);
    if(!gl) { alert("WebGL isn't available"); }
    
    gl.bufferData(gl.ARRAY_BUFFER, 2 * 4, flatten(startPosition)); //e como se fosse alocar um arrray

  
    // Three vertices
    var vertices = [
        vec2(-0.5,-0.5),
        vec2(0.5,-0.5),
        vec2(0,0.5)
    ];
    
    // Configure WebGL
    gl.viewport(0,0,canvas.width, canvas.height);
    gl.clearColor(1.0, 1.0, 1.0, 1.0);
    
    // Load shaders and initialize attribute buffers
    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    // Load the data into the GPU
    var bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(vertices), gl.STATIC_DRAW); //alternativa para esepcificar o numero de bytes que queremos bufferData(-, 1000*2*1*4 ,-) quero que ele aguente x numero de bytes  1000 verticies duas coordenadas 1float 4 bytes/ o traco signifca que e igual ao original 

    // Associate our shader variables with our data buffer
    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vPosition);
    
    setupCallbacks(canvas);
    
    render();
}
//canvas a partir deste momento que houver um evento em que haja um click tu fazes esta funcao
function setupCallbacks(canvas){
    canvas.addEventListener("mousedown", function(e){
    console.log("Mouse pressed");
        if(e.which == 1) {//se for zero e esquerda/ 1 e direita  verifica se o button e esquerda
            startPosition = vec2(-1 + 2*(e.offsetX/canvas.width), -1 + 2* (canvas.height -e.offsetY)/canvas.height);
            isDrawing = true;
                    
            console.log("StartPosition:" + startPosition);
            
        }
        console.log(isDrawing);
        
});
 canvas.addEventListener("mouseup", function(e){
     console.log("Mouse released");
     
     isDrawing = false;
});
canvas.addEventListener("mousemove", function(e){
    console.log("Mouse moving");
    endPosition = vec2(-1 + 2*(e.offsetX/canvas.width), -1 + 2* (canvas.height -event.offsetY)/canvas.height);
    console.log("endPosition:" + endPosition);
});
}
function render() {
    if(isDrawing){
    gl.clear(gl.COLOR_BUFFER_BIT);
    gl.drawArrays(gl.LINES, 0, 3);
}}

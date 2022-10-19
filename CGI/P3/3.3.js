var gl;
var dragging = false;
var mousedown = false;

window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = WebGLUtils.setupWebGL(canvas);
    if(!gl) { alert("WebGL isn't available"); }
    
    // Configure WebGL
    gl.viewport(0,0,canvas.width, canvas.height);
    gl.clearColor(0.0, 0.0, 0.0, 1.0);
    
    // Load shaders and initialize attribute buffers
    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);
    

    // Load the data into the GPU
    var bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
    gl.bufferData(gl.ARRAY_BUFFER, sizeof['vec2']*2, gl.DYNAMIC_DRAW);
    console.log(sizeof['vec2']);
    
    //Vertices
    canvas.onmousedown = function(e){
        if(e.which == 1){ //left mouse button clicked
            mousedown = true;
            var draw = getRelativeCoords(e , canvas);
            gl.bufferSubData(gl.ARRAY_BUFFER, 0, flatten(draw));
            gl.bufferSubData(gl.ARRAY_BUFFER, sizeof['vec2'], flatten(draw));
        }
    };
    
   canvas.onmousemove = function(e){
        if(mousedown){ //left mouse button clicked
            dragging = true;
            var draw = getRelativeCoords(e , canvas);
            gl.bufferSubData(gl.ARRAY_BUFFER, sizeof['vec2'], flatten(draw));
        }
    };
    
    canvas.onmouseup = function(e){
        if(e.which == 1){ //left mouse button clicked
            mousedown = false;
            dragging = true;
            var draw = getRelativeCoords(e , canvas);
            gl.bufferSubData(gl.ARRAY_BUFFER, sizeof['vec2'], flatten(draw));
        }
    };
    
    // Associate our shader variables with our data buffer
    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vPosition);
    render();
}

function getRelativeCoords(event, canvas) {
    return vec2(-1 + 2 * event.offsetX/canvas.width, -1 + 2 * (canvas.height - event.offsetY)/canvas.height);
   
}


function render() {
    gl.clear(gl.COLOR_BUFFER_BIT);
    if(dragging){
        gl.drawArrays(gl.LINE_STRIP, 0, 2);
    }
    requestAnimFrame(render);
}
var gl;
var over = 0;
var dragging = false;
var mousedown = false;
var currentTime = 0.0;
var endPos = [0.0,0.0];
var startPos = [0.0,0.0];
var velocity = 0.0;
var angle = 0.0;
var dx = 0.0;
var dy = 0.0;

window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = WebGLUtils.setupWebGL(canvas);
    if(!gl) { alert("WebGL isn't available"); }

    // Configure WebGL
    gl.viewport(0,0,canvas.width, canvas.height);
    gl.clearColor(0.0, 0.0, 0.0, 1.0);

    // Load shaders and initialize attribute buffers
    var programParticle = initShaders(gl, "vertex-shader-particle", "fragment-shader");
    var programLine = initShaders(gl, "vertex-shader-line", "fragment-shader");
    gl.useProgram(programParticle);

    dx = gl.getUniformLocation(programParticle, "dx");
    dy = gl.getUniformLocation(programParticle, "dy");

    // Load the data into the GPU
    var bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
    gl.bufferData(gl.ARRAY_BUFFER, sizeof['vec2']*2, gl.DYNAMIC_DRAW);

    //Vertices
    canvas.onmousedown = function(e){
        if(e.which == 1){ //left mouse button clicked
            mousedown = true
            over = 0;
            var draw = getFrameworkCoords(e , canvas);
            startPos = draw;
            console.log(startPos);
            gl.bufferSubData(gl.ARRAY_BUFFER, 0, flatten(draw));
            gl.bufferSubData(gl.ARRAY_BUFFER, sizeof['vec2'], flatten(draw));
        }
    };

   canvas.onmousemove = function(e){
        if(mousedown){ //left mouse button clicked
            dragging = true;
            over = 1;
            gl.useProgram(programLine);
            var draw = getFrameworkCoords(e , canvas);
            gl.bufferSubData(gl.ARRAY_BUFFER, sizeof['vec2'], flatten(draw));
            gl.useProgram(programParticle);
        }
    };

    canvas.onmouseup = function(e){
        if(e.which == 1){ //left mouse button clicked
            mousedown = false;
            dragging = false;
            over +=1;
            currentTime = 0;
            var draw = getFrameworkCoords(e , canvas);
            endPos = draw;
            gl.bufferSubData(gl.ARRAY_BUFFER, sizeof['vec2'], flatten(draw));
            getAlpha();
            getVelocity();
        }
    };

    // Associate our shader variables with our data buffer
    var pPosition = gl.getAttribLocation(programParticle, "pPosition");
    gl.vertexAttribPointer(pPosition, 2, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(pPosition);

    render();
}

function getFrameworkCoords(event, canvas) {
    return vec2(-1 + 2 * event.offsetX/canvas.width, -1 + 2 * (canvas.height - event.offsetY)/canvas.height);

}

function getVelocity(){//vo = 1.41?? parada
  return velocity = (Math.sqrt(Math.pow(2, endPos[0] - startPos[0]) + Math.pow(2, endPos[1] - startPos[1])))*0.2;
}

function getAlpha(){//feito
    return angle = (Math.atan2(endPos[1] - startPos[1], endPos[0] - startPos[0]));
}

function getMaxHeight(){
    return (Math.pow(2, velocity)*Math.pow(2, Math.sin(angle)))/0.02;
}

function getMaxReach(){
    return (Math.pow(2, velocity)*Math.sin(angle*2))/0.01;
}

function getMaxTime(){
    return (Math.sin(angle) * velocity)/0.01;
}



function render() {
    gl.clear(gl.COLOR_BUFFER_BIT);
   if(mousedown){
        gl.drawArrays(gl.POINTS, 0, 1);
      }
    if(dragging){
        gl.drawArrays(gl.LINE_STRIP, 0, 2);
      }
    if(over == 2){
        gl.drawArrays(gl.POINTS, 0, 1);
        gl.uniform1f(dx, (velocity*Math.cos(angle))*currentTime);
        gl.uniform1f(dy, (velocity*Math.sin(angle)*currentTime) - 0.008*Math.pow(2, currentTime));
        currentTime += 0.04;
      }
  //  if(currentTime == getMaxTime())
    //explosao
    requestAnimFrame(render);
}

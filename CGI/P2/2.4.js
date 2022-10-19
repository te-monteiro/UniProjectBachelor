var gl;
var i = 0;

window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = WebGLUtils.setupWebGL(canvas);
    if(!gl) { alert("WebGL isn't available"); }
    
    var points = [];
    var center = vec4(1.0, 0.0, 0, 1); 
    var r = 0.8;
    points.push(center);
    for (var  i = 0; i <= 202; i++){
        points.push(add(center,vec4(
        r*Math.cos(i*2*Math.PI/200),
        r*Math.sin(i*2*Math.PI/200),
        0,1.0 
    )));
    }
    
    for(var j = 0; j <= 202; j++){
        points.push(vec4(randomIntFromInterval(-1,1),randomIntFromInterval(-1,1),0,1));
    }
    
    // Configure WebGL
    gl.viewport(0,0,canvas.width, canvas.height);
    gl.clearColor(1.0, 1.0, 1.0, 1.0);
    
    // Load shaders and initialize attribute buffers
    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    // Load the data into the GPU
    var bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(points), gl.STATIC_DRAW);

    // Associate our shader variables with our data buffer
    
    time = gl.getUniformLocation(program, "u_time");
    
    
    var vCircle = gl.getAttribLocation(program, "circle");
    gl.vertexAttribPointer(vCircle, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vCircle);

    var vRandom = gl.getAttribLocation(program, "random");
    gl.vertexAttribPointer(vRandom, 4, gl.FLOAT, false, 0, 202*4*4);
    gl.enableVertexAttribArray(vRandom);
    
    
    render();
}

function randomIntFromInterval(min, max) { // min and max included 
  return Math.random() * (max - min) + min;
}

function render() {
    gl.clear(gl.COLOR_BUFFER_BIT);
    gl.uniform1f(time, i/500);
    gl.drawArrays(gl.LINE_LOOP, 1, 201);
    i++;
    requestAnimFrame(render);
}
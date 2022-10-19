var gl;
//var dx;
//var frame = 0;
//var vcolor;

window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = WebGLUtils.setupWebGL(canvas);
    if(!gl) { alert("WebGL isn't available"); }
    
    // Three vertices
    var vertices = new Float32Array ([
        -0.5,-0.5,
        1.0, 0.0, 0.0, 1.0,
        0.5,-0.5,
        0.0, 1.0, 0.0, 1.0,
        0,0.5,
        0.0, 0.0, 1.0, 1.0
    ]);
    
  //  var vertexColors = [
    //    vec2(-0.5,-0.5),
    //    vec2(0.5,-0.5),
      //  vec2(0,0.5),
    //    vec4(1.0, 0.0, 0.0, 1.0),
      //  vec4(0.0, 1.0, 0.0, 1.0),
        //vec4(0.0, 0.0, 1.0, 1.0)
//    ];  // ter este ou o de cima e a mesma coisa so sao apresentados de forma diferente
    
    // Configure WebGL
    gl.viewport(0,0,canvas.width, canvas.height);
    gl.clearColor(1.0, 1.0, 1.0, 1.0);
    
    // Load shaders and initialize attribute buffers
    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);
    
//    dx = gl.getUniformLocation(program, "dx");

    // Load the data into the GPU
    var bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
    gl.bufferData(gl.ARRAY_BUFFER, vertices, gl.STATIC_DRAW);

    // Associate our shader variables with our data buffer
    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, 6*4 , 0);
    gl.enableVertexAttribArray(vPosition);
    
   // var colorBuffer = gl.createBuffer();
    //gl.bindBuffer(gl.ARRAY_BUFFER, colorBuffer);
    //gl.bufferData(gl.ARRAY_BUFFER, flatten(vertexColors), gl.STATIC_DRAW);
    
    var vcolor = gl.getAttribLocation(program, "vcolor");
    gl.vertexAttribPointer(vcolor, 4, gl.FLOAT, false, 6*4, 2*4); 
    gl.enableVertexAttribArray(vcolor);
    
    render();
    //requestAnimationFrame(render);
}

function render() {
    gl.clear(gl.COLOR_BUFFER_BIT);
    gl.drawArrays(gl.TRIANGLES, 0, 3);
   
}
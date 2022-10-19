var gl;
var dragging = false;
var mousedown = false;
var endPos;
var startPos;
var time = 0.0;
var programLine;
var programParticle;
var timePassed;
var pPosition;
var velocity;
var timeLaunched;
var launched;
var lPosition;
var lineBuffer;
var pointBuffer;
var over = 0;
var counter = 0;
var vel;
var explodeVelocity;
var angle;
var num = 0;
var color = 0;

var colors = [
        vec4( 0.8, 0.8, 0.8, 0.7 ),  // gray
        vec4( 1.0, 0.0, 0.0, 0.7 ),  // red
        vec4( 1.0, 1.0, 0.0, 0.7 ),  // yellow
        vec4( 0.0, 1.0, 0.0, 0.7 ),  // green
        vec4( 0.0, 0.0, 1.0, 0.7 ),  // blue
        vec4( 1.0, 0.0, 1.0, 0.7 ),  // magenta
        vec4( 0.0, 1.0, 1.0, 0.7 )   // cyan
    ];


//var color = [0.0, 0.0, 0.0];


    window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = WebGLUtils.setupWebGL(canvas);
    if(!gl) { alert("WebGL isn't available"); }

    gl.viewport(0,0,canvas.width, canvas.height);
    gl.clearColor(0.0, 0.0, 0.0, 1.0);
        
        
        
        
    programParticle = initShaders(gl, "vertex-shader-particle", "fragment-shader");
    programLine = initShaders(gl, "vertex-shader-line", "fragment-shader");
        
    timePassed = gl.getUniformLocation(programParticle, "timePassed");
    explodeVelocity = gl.getUniformLocation(programParticle, "explodeVelocity");
    angle = gl.getUniformLocation(programParticle, "angle");

    gl.useProgram(programLine);
    lineBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, lineBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, sizeof['vec2']*2, gl.DYNAMIC_DRAW);

    lPosition = gl.getAttribLocation(programLine, "lPosition");
    gl.vertexAttribPointer(lPosition, 2, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(lPosition);

    gl.useProgram(programParticle);
    pointBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, pointBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, (2*sizeof['vec2'] + 4 + sizeof['vec4'])*65000, gl.DYNAMIC_DRAW);

    pPosition = gl.getAttribLocation(programParticle, "pPosition");
    gl.vertexAttribPointer(pPosition, 2, gl.FLOAT, false, 2*sizeof['vec2']+4+sizeof['vec4'], 0);
    gl.enableVertexAttribArray(pPosition);

    velocity = gl.getAttribLocation(programParticle, "velocity");
    gl.vertexAttribPointer(velocity, 2, gl.FLOAT, false, 2*sizeof['vec2']+4+sizeof['vec4'], sizeof['vec2']);
    gl.enableVertexAttribArray(velocity);

    timeLaunched = gl.getAttribLocation(programParticle, "timeLaunched");
    gl.vertexAttribPointer(timeLaunched, 1, gl.FLOAT, false, 2*sizeof['vec2']+4+sizeof['vec4'], 2*sizeof['vec2']);
    gl.enableVertexAttribArray(timeLaunched);
    
    
        
    color = gl.getAttribLocation(programParticle, "vColor");
    gl.vertexAttribPointer(color, 4, gl.FLOAT, false, 2*sizeof['vec2']+4+sizeof['vec4'], sizeof['vec2']);
    gl.enableVertexAttribArray(color);
    


    canvas.onmousedown = function(e) {
        if(e.which == 1) {
          mousedown = true;
          var draw = getFrameworkCoords(e, canvas);
          startPos = draw;
          changeToLineBuffer();
          gl.bufferSubData(gl.ARRAY_BUFFER, 0, new Float32Array([draw[0], draw[1]]));
        }
    };

    canvas.onmousemove = function(e) {
      if(mousedown){
        over = 1;
        dragging = true;
        changeToLineBuffer();
        var draw = getFrameworkCoords(e, canvas);
        gl.bufferSubData(gl.ARRAY_BUFFER, sizeof['vec2'], new Float32Array([draw[0], draw[1]]));
      }
    };

    canvas.onmouseup = function(e) {
      mousedown = false;
      dragging = false;
      over += 1;
      var draw = getFrameworkCoords(e, canvas);
      endPos = draw;
      changeToLineBuffer();
      gl.bufferSubData(gl.ARRAY_BUFFER, sizeof['vec2'], new Float32Array([draw[0], draw[1]]));
      changetoPointBuffer();
      vel = vec2((endPos[0]-startPos[0])*6, (endPos[1]-startPos[1])*6);
      for(var i = 0; i < 100; i++){
        gl.bufferSubData(gl.ARRAY_BUFFER, 2000*counter + 20*i, new Float32Array([startPos[0], startPos[1], vel[0], vel[1], time]));
          
        num++;
        color = vec4(colors[(num)%7]);
        gl.bufferSubData(gl.ARRAY_BUFFER, 2*sizeof['vec2']+4 +  i, color);  
          
        gl.uniform2f(explodeVelocity, Math.random()*2.0, Math.random()*3.0);
        gl.uniform1f(angle, 0.0628319*i);
      }
      counter += 1;
    };

    render();
    }


  function getFrameworkCoords(event, canvas) {
    return vec2(-1 + 2 * event.offsetX/canvas.width, -1 + 2 * (canvas.height - event.offsetY)/canvas.height);
}

  function getExplodeTime(){
      return vel[1] / 6.0;
    }


  function changetoPointBuffer(){
    gl.useProgram(programParticle);
    gl.bindBuffer(gl.ARRAY_BUFFER, pointBuffer);

    gl.vertexAttribPointer(pPosition, 2, gl.FLOAT, false, 2*sizeof['vec2']+4+sizeof['vec4'], 0);
    gl.enableVertexAttribArray(pPosition);

    gl.vertexAttribPointer(velocity, 2, gl.FLOAT, false, 2*sizeof['vec2']+4+sizeof['vec4'], sizeof['vec2']);
    gl.enableVertexAttribArray(velocity);

    gl.vertexAttribPointer(timeLaunched, 1, gl.FLOAT, false, 2*sizeof['vec2']+4+sizeof['vec4'], 2*sizeof['vec2']);
    gl.enableVertexAttribArray(timeLaunched);

    gl.vertexAttribPointer(color, 1, gl.FLOAT, false, 2*sizeof['vec2']+4*sizeof['vec4'], 2*sizeof['vec2']);
    gl.enableVertexAttribArray(color);
  }

  function changeToLineBuffer(){
    gl.useProgram(programLine);
    gl.bindBuffer(gl.ARRAY_BUFFER, lineBuffer);
    gl.vertexAttribPointer(lPosition, 2, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(lPosition);
      
  }

  function render() {
    gl.clear(gl.COLOR_BUFFER_BIT);
       
    time += 0.01;
    changetoPointBuffer();
    gl.uniform1f(timePassed, time);
  
    if(mousedown){
      changeToLineBuffer();
      gl.drawArrays(gl.POINTS, 0, 1);
    }
    if(dragging){
      changeToLineBuffer();
     //var colorLine = gl.getAttribLocation(programLine, "vColor");

     // gl.uniform4fv(colorLine, color);
      gl.drawArrays(gl.LINES, 0, 2);
    }
    if(over == 2 && counter == 0){
      changetoPointBuffer();
      var colorPoint = gl.getAttribLocation(programParticle, "vColor");
      gl.uniform4fv(colorPoint, color);
      gl.drawArrays(gl.POINTS, 0, 100);
    }
    if(counter > 0){
      changetoPointBuffer();
      var colorPoint = gl.getAttribLocation(programParticle, "vColor");
      
      gl.uniform4fv(colorPoint, color);
      gl.drawArrays(gl.POINTS, 0, 100*counter);
    }
    requestAnimFrame(render);
    }

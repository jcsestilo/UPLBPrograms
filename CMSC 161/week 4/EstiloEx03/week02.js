// Jan Coleen S. Estilo
// Exercise 03

const canvas = document.querySelector("#output");
const gl = canvas.getContext("webgl2");

const createShader = (gl, type, sourceCode) => {
    let shader = gl.createShader(type);
    gl.shaderSource(shader, sourceCode);
    gl.compileShader(shader);

    return shader;
};

const vertexShaderSource = document.querySelector("#vertex-shader").text;
const fragmentShaderSource = 
document.querySelector('#fragment-shader').text;

//function call to createShader; the return value is captured by vertexShader and fragmentShader
const vertexShader = createShader(gl, gl.VERTEX_SHADER, vertexShaderSource);
const fragmentShader = createShader(
    gl, 
    gl.FRAGMENT_SHADER, 
    fragmentShaderSource);

let program = gl.createProgram();

gl.attachShader(program, vertexShader);
gl.attachShader(program, fragmentShader);

gl.linkProgram(program);

gl.useProgram(program);

// Declaration of pointers to the attributes
const aPositionPointer = gl.getAttribLocation(program, 'a_position');
const aPointSizePointer = gl.getAttribLocation(program, 'a_point_size');
const aColorPointer = gl.getAttribLocation(program, 'in_color'); // get the attribute for the color

let myXYArrays = new Float32Array([
    
]);

let shapeBuffer = gl.createBuffer();

gl.bindBuffer(gl.ARRAY_BUFFER, shapeBuffer); // bind array buffer to shapeBuffer
gl.bufferData(gl.ARRAY_BUFFER, myXYArrays, gl.STATIC_DRAW); // input data

gl.vertexAttribPointer(aPositionPointer, 4, gl.FLOAT, false, 5 * Float32Array.BYTES_PER_ELEMENT, 0);
gl.enableVertexAttribArray(aPositionPointer);

gl.vertexAttribPointer(aPointSizePointer, 1, gl.FLOAT, false, 5*Float32Array.BYTES_PER_ELEMENT, 4*Float32Array.BYTES_PER_ELEMENT);
gl.enableVertexAttribArray(aPointSizePointer);

gl.vertexAttrib4f(aColorPointer, Math.random(), Math.random(), Math.random(), 1.0);

gl.drawArrays(gl.POINTS, 0, myXYArrays.length);


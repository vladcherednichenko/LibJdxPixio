#ifdef GL_ES
precision mediump float;
#endif

uniform vec4 u_color;

varying vec2 v_texCoord0;

void main() {
    gl_FragColor = u_color;
}

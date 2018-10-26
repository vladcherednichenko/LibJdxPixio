package test.pixiolibgdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class CubeShader implements Shader {
    ShaderProgram program;
    Camera camera;
    RenderContext context;
    int u_projViewTrans;
    int u_worldTrans;
    int u_color;

    @Override
    public void init() {

        String vert = Gdx.files.internal("shaders/vertex_shader.glsl").readString();
        String frag = Gdx.files.internal("shaders/fragment_shader.glsl").readString();
        program = new ShaderProgram(vert, frag);
        if (!program.isCompiled())
            throw new GdxRuntimeException(program.getLog());
        u_projViewTrans = program.getUniformLocation("u_projViewTrans");
        u_worldTrans = program.getUniformLocation("u_worldTrans");
        u_color = program.getUniformLocation("u_color");

    }

    @Override
    public void begin(Camera camera, RenderContext context) {

        this.camera = camera;
        this.context = context;
        program.begin();
        program.setUniformMatrix(u_projViewTrans, camera.combined);
        context.setDepthTest(GL20.GL_LEQUAL);
        context.setCullFace(GL20.GL_BACK);

    }

    @Override
    public void render(Renderable renderable) {

        program.setUniformMatrix(u_worldTrans, renderable.worldTransform);
        Color color = (Color)renderable.userData;
        program.setUniformf(u_color, color.r, color.g, color.b);
        renderable.meshPart.render(program);

    }

    @Override
    public int compareTo(Shader other) {
        return 0;
    }

    @Override
    public boolean canRender(Renderable instance) {
        return false;
    }


    @Override
    public void end() {
        program.end();
    }

    @Override
    public void dispose() {
        program.dispose();
    }
}

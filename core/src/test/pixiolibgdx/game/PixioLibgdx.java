package test.pixiolibgdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;


public class PixioLibgdx extends ApplicationAdapter {

	private Vector3 position = new Vector3();

	private Camera cam;
	private CameraInputController camController;

	private ModelBatch modelBatch;
	private ModelLoader modelLoader;

	private AssetManager assets;
	private Array<GameObject> instances = new Array<GameObject>();
	private boolean loading;

	private RenderContext renderContext;
	private Environment environment;
	private Renderable renderable;
	private Model model;

	private Shader shader;

	protected Stage stage;
	protected Label label;
	protected BitmapFont font;
	protected StringBuilder stringBuilder;
	private int visibleCount;

	@Override
	public void create () {

		Gdx.gl.glEnable(Gdx.gl.GL_CULL_FACE);
		Gdx.gl.glEnable(Gdx.gl.GL_DEPTH_TEST);
		Gdx.gl.glDepthMask(true);
		Gdx.gl.glDepthRangef(0, 50);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		stage = new Stage();
		font = new BitmapFont();
		label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
		stage.addActor(label);
		stringBuilder = new StringBuilder();

		environment = new Environment();
		modelBatch = new ModelBatch();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		//setup camera
		float screenRatio = (float)Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
		if(Settings.orthographicCamera){

			int orthographicCameraSize = 20;
			cam = new OrthographicCamera(orthographicCameraSize , orthographicCameraSize * screenRatio);

		}else{
			cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}

		cam.position.set(10f, 10f, 10f);
		cam.lookAt(0,0,0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);


		assets = new AssetManager();


        if(Settings.highPolyCube){
            assets.load("cube_detailed.obj", Model.class);
        }else{
            assets.load("cube_simple.obj", Model.class);
        }

        loading = true;



	}

	private void doneLoading(){

		Model cube;

		if(Settings.highPolyCube){
			cube = assets.get("cube_detailed.obj", Model.class);
		}else{
			cube = assets.get("cube_simple.obj", Model.class);
		}
//        ModelBuilder modelBuilder = new ModelBuilder();
//        cube = modelBuilder.createBox(1f, 1f, 1f,
//                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
//                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);


//		int [] model = {-1,240,-1,-1,-1,-1,-1,240,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,240,-1,-1,-1,-1,-1,240,-1,-1,-1,-1,-1,-1,-1,251,-1,-1,-1,-1,-1,251,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,251,-1,-1,-1,-1,-1,251,-1,-1,-1,-1,-1,-1,-1,252,-1,-1,-1,-1,-1,252,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,252,-1,-1,-1,-1,-1,252,-1,-1,-1,-1,-1,-1,-1,252,-1,-1,-1,-1,-1,252,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,252,-1,-1,-1,-1,-1,252,-1,-1,-1,-1,-1,-1,-1,252,252,-1,-1,-1,-1,252,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,252,252,-1,-1,-1,-1,252,-1,-1,-1,-1,-1,-1,-1,-1,252,252,-1,-1,-1,252,-1,-1,-1,-1,-1,-1,-1,-1,251,251,251,251,251,251,251,-1,-1,-1,-1,-1,-1,-1,251,251,251,251,251,251,251,-1,-1,-1,-1,-1,-1,-1,251,251,251,251,251,251,251,-1,-1,-1,-1,-1,-1,-1,252,252,-1,-1,-1,252,-1,-1,-1,-1,-1,-1,-1,252,252,252,-1,-1,-1,252,252,-1,-1,-1,-1,-1,-1,252,252,252,252,252,252,252,251,-1,-1,-1,-1,-1,-1,252,252,252,252,252,252,252,252,251,-1,-1,-1,-1,-1,252,252,252,252,252,252,252,251,-1,-1,-1,-1,-1,-1,252,252,252,-1,-1,-1,252,252,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,252,252,252,252,252,252,252,251,251,-1,-1,-1,-1,252,252,252,252,252,252,252,252,252,251,-1,-1,-1,-1,-1,252,252,252,252,252,252,252,252,252,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,251,251,-1,-1,-1,-1,252,-1,-1,-1,-1,-1,-1,-1,252,252,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,251,251,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,252,252,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,240,240,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,252,252,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,252,252,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,252,252,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,251,252,252,251,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,251,251,-1,-1,251,251,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,251,251,-1,-1,251,251,-1,-1,-1,-1,-1,-1,251,251,251,-1,-1,-1,-1,251,251,251,-1,-1,-1,-1,-1,-1,251,-1,-1,-1,-1,251,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,251,-1,-1,251,-1,-1,-1,-1,-1,-1,-1,251,-1,-1,-1,-1,-1,-1,-1,-1,251,-1,-1,-1,-1,-1,-1,251,-1,-1,-1,-1,251,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,251,-1,-1,-1,-1,251,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
//		int sizeX = 5;
//		int sizeZ = 14;

		int [] model = TestModels.coupleOnCouchModel;
		int sizeX = TestModels.coupleOnCouchSizeX;
		int sizeZ = TestModels.coupleOnCouchSizeZ;

		int sizeY = model.length / (sizeX*sizeZ);

		float startingPointX = (float)sizeX/2 - 0.5f;
		float startingPointY = (-1) * (float)sizeY / 2 + 0.5f;
		float startingPointZ = (-1) * (float)sizeZ / 2 + 0.5f;


		for (int i = 0; i< model.length; i++){

			if (model[i]>0){

				float x = startingPointX - (i/sizeZ ) + (i/ (sizeZ * sizeX))*sizeX;
				float y = startingPointY + (int)i/(sizeX * sizeZ);
				float z = startingPointZ + i % sizeZ;

				GameObject instance = new GameObject(cube);
				instance.transform.setToTranslation(x, y, z);
				PixioColor color = new PixioColor(PixioHelper.colorCodeToHex.get(model[i]));
				instance.materials.get(0).set(ColorAttribute.createDiffuse(new Color(color.RED, color.GREEN, color.BLUE, 1)));
				instances.add(instance);

			}


		}

        loading = false;
	}

	@Override
	public void render () {

		Gdx.gl.glEnable(Gdx.gl.GL_CULL_FACE);
		Gdx.gl.glEnable(Gdx.gl.GL_DEPTH_TEST);

		if (loading && assets.update()){ doneLoading(); }
		camController.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if(Settings.antialiasing) {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
		}else{
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		}

		visibleCount = 0;
//		for (final GameObject instance : instances) {
//			if (isVisible(cam, instance)) {
//				modelBatch.render(instance, environment);
//				visibleCount++;
//			}
//		}


		//modelBatch.render(instances, environment);


		modelBatch.begin(cam);
		modelBatch.render(instances, environment);
		modelBatch.end();

		//fps counter
		stringBuilder.setLength(0);
		stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());
		stringBuilder.append(" Visible: ").append(visibleCount);
		label.setText(stringBuilder);
		stage.draw();

	}

	protected boolean isVisible(final Camera cam, final GameObject instance) {
		instance.transform.getTranslation(position);
		position.add(instance.center);
		return cam.frustum.boundsInFrustum(position, instance.dimensions);
	}

	@Override
	public void dispose () {

		modelBatch.dispose();
		instances.clear();
		assets.dispose();
		shader.dispose();;

	}

	public void resume () {
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	public void pause () {
	}

	public static class GameObject extends ModelInstance {
		public final Vector3 center = new Vector3();
		public final Vector3 dimensions = new Vector3();
		public final float radius;

		private final static BoundingBox bounds = new BoundingBox();

		public GameObject(Model model) {
			super(model);
			calculateBoundingBox(bounds);
			bounds.getCenter(center);
			bounds.getDimensions(dimensions);
			radius = dimensions.len() / 2f;
		}
	}
}

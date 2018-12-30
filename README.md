# Lewy3dEngine
A Java 3D game framework built using the LibGDX 3D API. This framework provides simple-to-use objects that allows you
to develop your 3D games with ease.

The framework mimics the Unity3D scripting engine and adopts the simplicity of interacting with game objects.

The framework is still in development, but so far has;
- First person character controller
- Cameras and post processing
- Lighting
- Skyboxes
- RigidBodies

### Adding a component to a game object
Just like unity, you simple call the addComponent method and pass in the component you wish to add.
```
Cube cube = new Cube(Color.RED, 10, 10, 10);    //A red cube with a width, height and depth of 10
cube.name = "cube";
cube.addComponent(new RigidBody());

Instantiate(cube);
```

### To create a first person controller
```
FirstPersonController player = new FirstPersonController();
player.name = "Player";
player.getCamera().postProcessor.applyProfile(new PostProcessorProfileExample());

Instantiate(cube);
```

### To change scenes
```
SceneManager.LoadScene(new Level2());
```

### To add a new camera to the scene
```
Camera CCTV = new Camera();
CCTV.transform.position.x = 5;
CCTV.transform.position.y = 5;
CTV.transform.position.z = 0;
CCTV.getCamera().lookAt(player.transform.position); //Look at the player

Instantiate(CCTV);
```
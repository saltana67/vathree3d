import * as THREE from "three";

class ThreeTest2 {

  init(element){
	this.canvas = element;
	this.renderer = new THREE.WebGLRenderer( { antialias : true, canvas: this.canvas } );
	this.cameraDistance = 100;
	this.camera = new THREE.PerspectiveCamera( 50, this.canvas.width / this.canvas.height, 1, 400 );
	this.cameraAngle=0;
	this.scene = new THREE.Scene();
	//this.splineGeometry = new THREE.Geometry();
	this.splineGeometry = new THREE.BufferGeometry();
	this.splineMaterial = new THREE.LineBasicMaterial({
	    color: 0x6FC0BA,
	    opacity: 0,
	    transparent: true
	});

	//this.backdropGeometry = new THREE.Geometry();
	this.backdropGeometry = new THREE.BufferGeometry();
	this.backdropMaterial = new THREE.LineBasicMaterial({
	    color: 0x1b2f2d,
	    opacity: 1,
	    transparent: true
	});

	this.cameraUp = false;
	
	this.renderer.setSize(this.canvas.width, this.canvas.height);
	this.camera.position.z = this.cameraDistance;
	this.camera.lookAt(this.scene.position);

	this.lastRenderDate = new Date();
	
	var calc = function(x){
		return (x+200)*(x+100)*(x+280)*(x+10)*(x-300)*(x-250)*(x-150) / Math.pow(10,14)/1.5;
	}

	var splineVertices = new Float32Array( 500 * 3 ); // three components per vertex
	// components of the position vector for each vertex are stored
	// contiguously in the buffer.
	var bufferIndex = 0;
    for(var i = 0; i< 500; i++){
		const x = i - 250;
        const y = calc(i-250) * Math.sin(2 * Math.PI * (i % 6) / 6 + i/500) + Math.cos(i) * 5;
        const z = calc(i-250) * Math.cos(2 * Math.PI * (i % 6) / 6 + i/500);
        //this.splineGeometry.vertices.push(new THREE.Vector3(i - 250, y, z));
		splineVertices[bufferIndex++] = x;
		splineVertices[bufferIndex++] = y;
		splineVertices[bufferIndex++] = z;
    }
	// itemSize = 3 because there are 3 values (components) per vertex
	var spa = new THREE.BufferAttribute( splineVertices, 3 );
	this.splineGeometry.setAttribute( 'position', spa );
    this.splineGeometry.verticesNeedUpdate = true;
	this.splineLine = new THREE.Line(this.splineGeometry, this.splineMaterial);
	this.scene.add(this.splineLine);

	var backdropVertices = new Float32Array( 25 * 2 * 3 ); // three components per vertex
	bufferIndex = 0;
	for(var i = 0; i< 25; i++){
		//this.backdropGeometry.vertices.push(new THREE.Vector3(-500,100-i*8,-100));
		backdropVertices[bufferIndex++] = -500;
		backdropVertices[bufferIndex++] = 100-i*8;
		backdropVertices[bufferIndex++] = -100;
		//this.backdropGeometry.vertices.push(new THREE.Vector3(500,100-i*8,-100));
		backdropVertices[bufferIndex++] = 500;
		backdropVertices[bufferIndex++] = 100-i*8;
		backdropVertices[bufferIndex++] = -100;
	}
	// itemSize = 3 because there are 3 values (components) per vertex
	var bba = new THREE.BufferAttribute( backdropVertices, 3 );
	this.backdropGeometry.setAttribute( 'position', bba );
	this.backdropLine = new THREE.Line(this.backdropGeometry, this.backdropMaterial, THREE.LinePieces);
	this.scene.add(this.backdropLine);
	
	this.firstRun = null;
	this.introAnimationDone = false;
  }
  
  render() {
    this.renderer.render(this.scene, this.camera);
  }
  
  animate() {
    requestAnimationFrame(this.animate.bind(this));
	
	if(this.firstRun === null){
	    this.firstRun = Date.now() + 2500;
	}
	// renderer.render( this.scene, this.camera );
	var renderTime = new Date() - this.lastRenderDate;
	var timeSinceStart = Date.now() - this.firstRun;
	this.lastRenderDate = new Date();

	var rotateCameraBy = (2 * Math.PI)/(10000/renderTime);
	this.cameraAngle += rotateCameraBy;

	if(timeSinceStart < 3000){
	    this.backdropMaterial.opacity = Math.max(0,(timeSinceStart-2000)/3000);
	    this.splineMaterial.opacity = timeSinceStart/3000;
	} else if(!this.introAnimationDone){
	    this.introAnimationDone = true;
	    this.backdropMaterial.opacity = .333;
	    this.splineMaterial.opacity = 1;
	}

//	if(lastMessageTime !== null && !dataStreamOn){
//	    dataStreamOn = true;
//	    $("#datalink-status").text("CONNECTED");
//	    $("#datalink-status").css("color", "green");
//	}
//
//	if(Math.random() < .1){
//	    if((lastMessageTime === null && timeSinceStart > 8000) || (lastMessageTime !== null && Date.now() - lastMessageTime > 8000)){
//	        $("#datalink-status").text("ERROR");
//	        $("#datalink-status").css("color", "red");
//	    } 
//	}


	this.camera.position.x = Math.sin(this.cameraAngle) * 20;

	this.render();

	this.splineLine.rotation.x += .01;
  }
}

window.initThree2 = function(element) {
  // Called from Java with the DOM element for the Three component instance
  const tt = new ThreeTest2();
  tt.init(element);
  tt.animate();
};
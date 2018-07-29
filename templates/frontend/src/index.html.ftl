<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <title>Ngmaterial2</title>
  <base href="/">

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/x-icon" href="favicon.ico">

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">

  <style>
	html {
	    height: 100%;
	}
	
	body {
	    min-height: 100%;
	    background-color: #3f51b5;
	}
	
	.loading-page {
	    height: 100%;
	    width: 100%;
	    position: absolute;
	    background: #3f51b5;
	}
	  .spinner {
	    position: absolute;
	    left: 48%;
	    top: 40%;
	    text-align: center;
	    margin: 20px auto;
	    width: 40px;
	    height: 40px;
	    -webkit-transform: rotateZ(45deg);
	    transform: rotateZ(45deg);
	}
	
	.spinner .sk-cube {
	    float: left;
	    width: 50%;
	    height: 50%;
	    position: relative;
	    -webkit-transform: scale(1.1);
	    -ms-transform: scale(1.1);
	    transform: scale(1.1);
	}
	
	.spinner .sk-cube:before {
	    content: '';
	    position: absolute;
	    top: 0;
	    left: 0;
	    width: 100%;
	    height: 100%;
	    background-color: white;
	    -webkit-animation: sk-foldCubeAngle 2.4s infinite linear both;
	    animation: sk-foldCubeAngle 2.4s infinite linear both;
	    -webkit-transform-origin: 100% 100%;
	    -ms-transform-origin: 100% 100%;
	    transform-origin: 100% 100%;
	}
	
	.spinner .sk-cube2 {
	    -webkit-transform: scale(1.1) rotateZ(90deg);
	    transform: scale(1.1) rotateZ(90deg);
	}
	
	.spinner .sk-cube3 {
	    -webkit-transform: scale(1.1) rotateZ(180deg);
	    transform: scale(1.1) rotateZ(180deg);
	}
	
	.spinner .sk-cube4 {
	    -webkit-transform: scale(1.1) rotateZ(270deg);
	    transform: scale(1.1) rotateZ(270deg);
	}
	
	.spinner .sk-cube2:before {
	    -webkit-animation-delay: 0.3s;
	    animation-delay: 0.3s;
	}
	
	.spinner .sk-cube3:before {
	    -webkit-animation-delay: 0.6s;
	    animation-delay: 0.6s;
	}
	
	.spinner .sk-cube4:before {
	    -webkit-animation-delay: 0.9s;
	    animation-delay: 0.9s;
	}
	
	@-webkit-keyframes sk-foldCubeAngle {
	    0%, 10% {
	        -webkit-transform: perspective(140px) rotateX(-180deg);
	        transform: perspective(140px) rotateX(-180deg);
	        opacity: 0;
	    }
	    25%, 75% {
	        -webkit-transform: perspective(140px) rotateX(0deg);
	        transform: perspective(140px) rotateX(0deg);
	        opacity: 1;
	    }
	    90%, 100% {
	        -webkit-transform: perspective(140px) rotateY(180deg);
	        transform: perspective(140px) rotateY(180deg);
	        opacity: 0;
	    }
	}
	
	@keyframes sk-foldCubeAngle {
	    0%, 10% {
	        -webkit-transform: perspective(140px) rotateX(-180deg);
	        transform: perspective(140px) rotateX(-180deg);
	        opacity: 0;
	    }
	    25%, 75% {
	        -webkit-transform: perspective(140px) rotateX(0deg);
	        transform: perspective(140px) rotateX(0deg);
	        opacity: 1;
	    }
	    90%, 100% {
	        -webkit-transform: perspective(140px) rotateY(180deg);
	        transform: perspective(140px) rotateY(180deg);
	        opacity: 0;
	    }
	}
  </style>
  
</head>

<body class="mat-typography">
  <app-base>

    <div class="loading-page">
      <div class="spinner">
          <div class="sk-cube1 sk-cube"></div>
          <div class="sk-cube2 sk-cube"></div>
          <div class="sk-cube4 sk-cube"></div>
          <div class="sk-cube3 sk-cube"></div>
      </div>
   </div>

   </app-base>
</body>

</html>





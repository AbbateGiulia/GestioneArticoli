/**
 * 
 */
// Non fa submit se dei campi non sono validi e applica css
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // prende gli elementi a cui applichiamo lo style (ovvero tutto perche la class needsV è nel tag form)
	  var forms = document.getElementsByClassName('needs-validation');    
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) { //iteratore
    	form.addEventListener('submit', function(event) { //listener su event submit
    		var n = form.prezzo.value; 
    		console.log(n);
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation(); //blocca submit
        }
        if (isNaN(n) == true) { 
        	alert('il prezzo deve essere un numero');
        	console.log("NAN");
	    	event.preventDefault();
	        event.stopPropagation(); //blocca submit     
	    } 
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();


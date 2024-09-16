const express = require('express')
const app = express()
const port = 8080
const fs = require('fs');

app.use(express.json());



//Hace un request de una nueva renta y lo añade a la lista
app.post('/newrental', (req, res, next) => {
	//Leo JSON
	try{
		carrentalFileRawData = fs.readFileSync('carrental.json');
       		 carrentalJSON = JSON.parse(carrentalFileRawData);
	}
	catch{
	//Si esta vacio lo creo
		carrentalJSON = {"carrental": []};
	}
	console.log(req.body);
	//GUARDAR en JSON
	carrentalJSON['carrental'].push({"car_maker": req.body.car_maker, "car_model": req.body.car_model, "number_of_days": req.body.number_of_days, "number_of_units": req.body.number_of_units});
	fs.writeFileSync("carrental.json", JSON.stringify(carrentalJSON));
	res.status(201);
	res.end();
})




//LEE el JSON y lo printa en la url http://localhost:8080/listar
app.get('/listar', (req, res, next) => {
        //LEER DEL JSON
	try{
       		carrentalFileRawData = fs.readFileSync('carrental.json');
      	 	carrentalJSON = JSON.parse(carrentalFileRawData);
		res.json(
                	carrentalJSON
       		);
	}catch{
		res.send("Registro de rentals vacio");
	}

        res.status(200);
        res.end();
})



app.listen(port, () => {
  console.log(`PTI HTTP Server listening at http://localhost:${port}`)
})




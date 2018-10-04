package com.apap.tutorial4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.service.CarService;
import com.apap.tutorial4.service.DealerService;

@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value="/car/add/{dealerId}", method= RequestMethod.GET)
	private String add(@PathVariable(value="dealerId") Long dealerId, Model model) {
		CarModel car = new CarModel();
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		car.setDealer(dealer);
		model.addAttribute("car", car);
		return "addCar";
	}
	
	@RequestMapping(value="/car/add",method = RequestMethod.POST)
	private String addCarSubmit(@ModelAttribute CarModel car) {
		carService.addCar(car);
		return "add";
	}
	
	@RequestMapping(value="car/delete/{carId}", method= RequestMethod.GET)
	private String deleteCar(@PathVariable(value="carId") Long carId,Model model) {
		CarModel car = carService.getCarDetailById(carId).get();
		carService.deleteCar(car);
		return "deleteCar";
	}
	
	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.GET)
	private String update(@PathVariable(value = "carId") Long carId, Model model) {
		CarModel car = carService.getCarDetailById(carId).get();
		CarModel newCar = new CarModel();
		newCar.setId(car.getId());
		model.addAttribute("car",newCar);
		model.addAttribute("oldCar",car);
		return "UpdateCarForm";
	}
	
	@RequestMapping(value = "/car/update", method = RequestMethod.POST)
	private String updateCarSubmit(@ModelAttribute CarModel car) {
		CarModel submitCar = carService.getCarDetailById(car.getId()).get();
		submitCar.setAmount(car.getAmount());
		submitCar.setBrand(car.getBrand());
		submitCar.setPrice(car.getPrice());
		submitCar.setType(car.getType());
		carService.addCar(submitCar);
		return "update";
	}
	
	
}

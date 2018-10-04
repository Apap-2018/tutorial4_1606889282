package com.apap.tutorial4.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial4.model.CarModel;


public interface CarService {
	Optional<CarModel> getCarDetailById(Long id);
	void addCar(CarModel car);
	void deleteCar(CarModel car);
	List<CarModel> getAllCar();
	
}

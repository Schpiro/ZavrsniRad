package com.bbzavrsni.zavrsni.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
}

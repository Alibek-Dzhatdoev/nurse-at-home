package com.ali.nurse_at_home.controller.v1;

import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/api/v1/nurses")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class NurseController {
}

package com.dashboard.app.personal_dashboard;

import com.dashboard.app.personal_dashboard.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {
    @Autowired
    private WeatherService weatherService;
    // Update your dashboard method:
    @GetMapping("/")
    public String dashboard(
            @RequestParam(value = "city", defaultValue = "London") String city,
            Model model) {

        model.addAttribute("weather", weatherService.getCurrentWeather(city));
        model.addAttribute("message", "Dashboard with real weather data!");
        return "dashboard";
    }
}
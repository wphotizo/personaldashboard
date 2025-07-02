package com.dashboard.app.personal_dashboard;

import com.dashboard.app.personal_dashboard.service.CountryService;
import com.dashboard.app.personal_dashboard.service.QuoteService;
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
    @Autowired
    private QuoteService quoteService;
    // Add CountryService field
    @Autowired
    private CountryService countryService;

    // Update constructor
    public DashboardController(WeatherService weatherService,
                               QuoteService quoteService,
                               CountryService countryService) {
        this.weatherService = weatherService;
        this.quoteService = quoteService;
        this.countryService = countryService;
    }

    // Update dashboard method to accept country parameter
    @GetMapping("/")
    public String dashboard(
            @RequestParam(value = "city", defaultValue = "London") String city,
            @RequestParam(value = "country", defaultValue = "United Kingdom") String country,
            Model model) {

        model.addAttribute("weather", weatherService.getCurrentWeather(city));
        model.addAttribute("quote", quoteService.getRandomQuote());
        model.addAttribute("countryInfo", countryService.getCountryInfo(country));  // Add this

        model.addAttribute("currentCity", city);
        model.addAttribute("currentCountry", country);  // For form defaults

        return "dashboard";
    }
}
package com.asi.sda.sample.controller;

import com.asi.sda.sample.model.Sample;
import com.asi.sda.sample.model.SampleRequestDto;
import com.asi.sda.sample.model.SampleResponseDto;
import com.asi.sda.sample.service.SampleThymeleafService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SampleThymeleafController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleThymeleafController.class);

    private final SampleThymeleafService service;

    @Autowired
    public SampleThymeleafController(SampleThymeleafService service) {
        this.service = service;
    }

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public String viewSamplesPage(Model model) {
        LOGGER.info("List all samples...");

        List<SampleResponseDto> samples = service.findAll();
        model.addAttribute("samples", samples);
        return "index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showNewSamplePage(Model model) {
        LOGGER.info("Show new sample page...");

        Sample sample = new Sample();
        model.addAttribute("sample", sample);
        return "new_sample";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSample(@ModelAttribute("sample") @Valid SampleRequestDto sample) {
        LOGGER.info("Save new sample...");
        service.create(sample);
        return "redirect:/samples";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditSamplePage(@PathVariable(name = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("edit_sample");
        SampleResponseDto sample = service.find(id);
        modelAndView.addObject("sample", sample);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteSample(@PathVariable(name = "id") Integer id) {
        service.delete(id);
        return "redirect:/samples";
    }
}

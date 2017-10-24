package com.tbell.calculatordb.controllers;

import com.tbell.calculatordb.Repositories.OperationRepository;
import com.tbell.calculatordb.Repositories.OperationUserRepository;
import com.tbell.calculatordb.models.Operation;
import com.tbell.calculatordb.models.OperationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MathController {

    @Autowired
    OperationRepository repo;
    @Autowired
    OperationUserRepository userRepo;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<OperationUser> userOperations = userRepo.findAll();
        model.addAttribute("operation_user", userOperations);
        return "home";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String newUser(
            @RequestParam("newUser")String newUser){
        OperationUser operationUser = new OperationUser();
        operationUser.setUsername(newUser);
        userRepo.save(operationUser);
        return "redirect:/calculator/" + operationUser.getId();
    }

    @RequestMapping(value = "/calculator/{userId}", method = RequestMethod.GET)
    public String calculator(@PathVariable("userId") long userId,
                             Model  model){
        OperationUser operationUser = userRepo.findOne(userId);
        Iterable<Operation> operations = repo.findAllByOperationUser_Id(userId);
        model.addAttribute("operation", operations);
        model.addAttribute("operation_user", operationUser);
        return "calculator";
    }

    @RequestMapping(value = "/operationCreate/{userId}", method = RequestMethod.POST)
    public String index(@PathVariable("userId") long userId,
                        @RequestParam(value="firstNum", required = false)String firstNum,
                        @RequestParam(value="operator", required = false)String operator,
                        @RequestParam(value="secNum", required = false)String secNum,
                        Model model) {

        OperationUser user = userRepo.findOne(userId);
        model.addAttribute("operation_user", user);

        if(operator.equals("/") && secNum.equals("0")) {
            model.addAttribute("error", "Are you trying to divide by 0?");
            model.addAttribute("operation_user", user);
            Iterable<Operation> operations = repo.findAllByOperationUser_Id(userId);
            model.addAttribute("operation", operations);
            return "calculator";
        } else {

            try {
                Double results = Operation.doMath(firstNum, secNum, operator);
                Operation math = new Operation(firstNum, secNum, operator, results, user);
                repo.save(math);
                model.addAttribute("resultsData", results);


            } catch (NumberFormatException ex) {
                model.addAttribute("operation_user", user);
                model.addAttribute("error", "Invalid Input");
                Iterable<Operation> operations = repo.findAllByOperationUser_Id(userId);
                model.addAttribute("operation", operations);
                return "calculator";
            }

            return "redirect:/calculator/" + userId;

        }
    }


}
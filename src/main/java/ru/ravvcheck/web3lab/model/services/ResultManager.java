package ru.ravvcheck.web3lab.model.services;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;
import org.primefaces.PrimeFaces;
import ru.ravvcheck.web3lab.dao.ResultDAO;
import ru.ravvcheck.web3lab.model.models.Result;

import java.text.SimpleDateFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Named("resultManager")
@SessionScoped
public class ResultManager implements Serializable {
    private ResultDAO resultDAO;
    private Result currentResult;
    private List<Result> results;

    public void init() {
        resultDAO = new ResultDAO();
        currentResult = new Result();
        results = resultDAO.getResults();
        if (results.size() > 0) {
            currentResult.setX(null);
            currentResult.setY(null);
            currentResult.setR(results.get(results.size() - 1).getR());
        } else {
            currentResult.setX(null);
            currentResult.setY(null);
            currentResult.setR(5.0);
        }
    }

    public void addResult() {
        if (currentResult.getY() > 5 || currentResult.getY() < 5) {
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        currentResult.setCurrentTime(dateFormat.format(new Date(System.currentTimeMillis())));
        currentResult.setHit(AreaCheck.isHit(currentResult.getX(), currentResult.getY(), currentResult.getR()));
        results.add(currentResult);
        resultDAO.save(currentResult);
        currentResult = new Result();
        currentResult.setX(null);
        currentResult.setY(null);
        currentResult.setR(results.get(results.size() - 1).getR());
    }

//    public void changeR() {
//        PrimeFaces primeFaces = PrimeFaces.current();
//        primeFaces.executeScript("redrawGraph();");
//        primeFaces.executeScript("clearDots();");
//    }
}

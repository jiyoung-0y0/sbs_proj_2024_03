package org.example.controller;

import org.example.container.Container;
import org.example.service.ExportService;

import java.util.Scanner;


public class ExportController extends Controller {

    private ExportService exportService;
    public ExportController() {
        exportService = Container.exportService;
    }

    @Override
    public void doAction(String cmd, String actionMethodName) {
        switch (actionMethodName){
            case "html":
                doHtml();
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다.");
                break;
        }
    }
    private void doHtml(){
        System.out.println("html를 생성합니다.");
        exportService.makeHtml();
    }
}

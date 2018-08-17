package cc.ccoder.springbootfileupload.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 全局异常
 * ControllerAdvice 能够处理controller上出现的异常，但是对于其他层，例如service层的异常则无能为力
 * @author chencong
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * https://jira.spring.io/browse/SPR-14651
     * 4.3.5 supports RedirectAttributes redirectAttributes
     * @param e
     * @param redirectAttributes
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public String handleError(MultipartException e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message",e.getCause().getMessage());
        return "redirect:/uploadStatus";
    }

}

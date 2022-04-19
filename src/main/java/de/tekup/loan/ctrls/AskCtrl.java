package de.tekup.loan.ctrls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.tekup.loan.services.CallServer;
import de.tekup.loan.soap.api.consume.loaneligibilty.CustomerRequest;
import de.tekup.loan.soap.api.consume.loaneligibilty.WsResponse;

@Controller
public class AskCtrl {
	
	@Autowired
	private CallServer service;
	
	@GetMapping("/test/client")
	public String getClientForm(Model model) {
		model.addAttribute("request", new CustomerRequest());
		return "ask";
	}
	
	@PostMapping("/test/client")
	public String postClientForm(Model model,@ModelAttribute("request") CustomerRequest request) {
		WsResponse response = service.callSoapServer(request);
		model.addAttribute("response", response);
		return "response";
	}

}

package com.srikanthdev.readmail.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srikanthdev.readmail.service.BootOutlookService;

@RestController
@RequestMapping("/outlookmail")
public class BootOutlookController {

	@Autowired
	BootOutlookService outlookService;

	@RequestMapping("/read")
	public void readMails() {
		outlookService.readMail();
	}

}

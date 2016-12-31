package com.srikanthdev.readmail.service;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BootOutlookService {

	private static final Log LOG = LogFactory.getLog(BootOutlookService.class);

	@Value("${outlook.username}")
	private String username;

	@Value("${outlook.password}")
	private String password;

	private static final String FOLDER = "INBOX";
	private static final String OUTLOOK_HOST = "outlook.office365.com";
	private static final String MAIL_PROTOCOL = "mail.store.protocol";
	private static final String IMAPS = "imaps";

	public void readMail() {
		Properties props = new Properties();
		props.setProperty(MAIL_PROTOCOL, IMAPS);
		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();

			store.connect(OUTLOOK_HOST, username, password);
			Folder inbox = store.getFolder(FOLDER);
			inbox.open(Folder.READ_ONLY);

			Message messages[] = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

			LOG.info("Unread Messages count : " + messages.length);

			for (Message message : messages) {
				LOG.info(message.getSubject());
				LOG.info(message.getAllRecipients());
				LOG.info(message.getFrom());
				LOG.info(message.getContent());
			}
		} catch (Exception e) {
			LOG.error("Error", e);
		}
	}
}

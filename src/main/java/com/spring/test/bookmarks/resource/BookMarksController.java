package com.spring.test.bookmarks.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.test.bookmarks.dao.UserRepository;
import com.spring.test.bookmarks.model.Messaging;
import com.spring.test.bookmarks.model.User;
import com.spring.test.bookmarks.model.WebHookRequest;

@RestController
public class BookMarksController {

	private static final String VERIFY_TOKEN = "QWERTY1234567ASDF";

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/hello")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping(path = "/all")
	public Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@GetMapping(path = "/add") // Map ONLY GET Requests
	public String addNewUser(@RequestParam String name, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path = "/webhook")
	public ResponseEntity<String> getMessages(@RequestParam String verify_token, @RequestParam String challenge,
			@RequestParam String mode) {

		// Checks if a token and mode is in the query string of the request
		if (mode != null && verify_token != null) {
			// Checks the mode and token sent is correct
			if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(verify_token)) {
				System.out.println("WEBHOOK_VERIFIED");
				return ResponseEntity.ok().body(challenge);
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body("Forbidden");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("The request did not pass");
		}

		/*
		 * Map<String, Object> map = new HashMap<>();
		 * 
		 * String statusMessage = "Success"; Integer statusCode =
		 * HttpStatus.ACCEPTED.value(); map.put("status_code", statusCode);
		 * map.put("status_message", statusMessage); return
		 * ResponseEntity.accepted().body(map);
		 */
	}
	
	@RequestMapping(path = "/webhook", method = RequestMethod.POST)
	public ResponseEntity<String> postWebHook(@RequestBody WebHookRequest request) {

		if (request != null) {
			String page = request.getObject();
			if ("page".equals(page)) {
				for (Messaging messagingEntry : request.getEntry()) {
					System.out.println("webhookEvent" + messagingEntry.getMessaging().get(0).getMessage());
				}
				return ResponseEntity.accepted().body("Request Received");
			} else {
				return ResponseEntity.badRequest().body("Bad Request");
			}
		} else {
			return ResponseEntity.badRequest().body("Bad Request");
		}
	}

}

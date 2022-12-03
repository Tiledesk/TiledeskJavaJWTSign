package jwt;

public class Sign {

	public static void main(String[] args) {
		
		// Tiledesk data
		String secret = "YOUR-SECRET-TILEDESK-PROJECT-SECRET"; // You Tiledesk project secret
		String projectId = "YOUR-TILEDESK-PROJECT-ID"; // Your Tiledesk projectId
		// Organization data
		String OrgUserId = "OrgUserId"; // Organizazion unique User id
		String OrgUserFirstname = "User Firstname"; // OPT
		String OrgUserLastname = "User Lastname"; // OPT
		String OrgUserEmail = "User Email"; // OPT
		// JWT data
		String issuer = "My Organization";
		String tiledeskUserId = projectId + "_" + OrgUserId;
		String subject = "userexternal";
		String aud = "https://tiledesk.com/projects/" + projectId;
		String jwt = TiledeskJWT.createJWT(
				tiledeskUserId,
				issuer,
				subject,
				aud,
				secret,
				OrgUserFirstname,
				OrgUserLastname,
				OrgUserEmail);
		System.out.println("JWT " + jwt);
	}

}
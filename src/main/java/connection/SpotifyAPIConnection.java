package connection;

import io.github.cdimascio.dotenv.Dotenv;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class SpotifyAPIConnection {
    private final Dotenv config = Dotenv.configure().load();
    private final String clientID = config.get("SPOTIFY_CLIENT_ID");
    private final String clientSecret = config.get("SPOTIFY_CLIENT_SECRET");

    public SpotifyAPIConnection() throws Exception {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientID)
                .setClientSecret(clientSecret)
                .build();

        ClientCredentialsRequest credentialsRequest = spotifyApi.clientCredentials().build();
        String accessToken = credentialsRequest.execute().getAccessToken();

        spotifyApi.setAccessToken(accessToken);
    }
}

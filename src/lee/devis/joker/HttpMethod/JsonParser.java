package lee.devis.joker.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lee.devis.joker.Entity.ImageSize;
import lee.devis.joker.Entity.Joke;
import lee.devis.joker.Entity.Result;
import lee.devis.joker.Entity.User;
import lee.devis.joker.Entity.Vote;

/**
 * Description:
 * Created by Devis on 14-7-16.
 */
public class JsonParser {


    public static Result getJokes(String jsonString) {
        Result result = new Result();
        int count;
        int currentPage;
        int totalCount;
        List<Joke> jokes = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            count = jsonObject.getInt("count");
            totalCount = jsonObject.getInt("total");
            currentPage = jsonObject.getInt("page");

            JSONArray jsonArray = jsonObject.getJSONArray("items");
            if (jsonArray != null && jsonArray.length() > 0) {
                jokes = new ArrayList<Joke>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Joke joke = new Joke();
                    joke.setImage(jsonArray.getJSONObject(i).getString("image"));
                    joke.setPublished_at(jsonArray.getJSONObject(i).getInt("published_at"));
                    joke.setTag(jsonArray.getJSONObject(i).getString("tag"));

                    User user = null;
                    Object userObject = jsonArray.getJSONObject(i).get("user");
                    if (userObject != JSONObject.NULL) {
                        JSONObject userJson = jsonArray.getJSONObject(i).getJSONObject("user");
                        if (userJson != null) {
                            user = new User();
                            user.setCreated_at(userJson.getString("created_at"));
                            user.setLast_device(userJson.getString("last_device"));
                            user.setRole(userJson.getString("role"));
                            user.setLast_visited_at(userJson.getString("last_visited_at"));
                            user.setState(userJson.getString("state"));
                            user.setLogin(userJson.getString("login"));
                            user.setId(userJson.getString("id"));
                            user.setIcon(userJson.getString("icon"));
                        }
                    }


                    joke.setUser(user);

                    ImageSize imageSize = null;
                    Object imageSizeObject = jsonArray.getJSONObject(i).get("image_size");
                    if (imageSizeObject != JSONObject.NULL) {
                        JSONObject imageSizeJson = jsonArray.getJSONObject(i).getJSONObject("image_size");
                        if (imageSizeJson != null) {
//                        imageSize = new ImageSize();
//                        imageSize.setS(imageSizeJson.getJSONArray("s").);
//                        "image_size":{
//                           "s":[
//                                  198,
//                                352,
//                                12631
//                           ],
//                            "m":[
//                             450,
//                                800,
//                                84727
//                         ]
//                       },
                        }
                    }
                    joke.setImage_size(imageSize);

                    joke.setId(jsonArray.getJSONObject(i).getString("id"));

                    Vote vote = null;
                    Object voteObject = jsonArray.getJSONObject(i).get("votes");
                    if (voteObject != JSONObject.NULL) {
                        JSONObject voteJson = jsonArray.getJSONObject(i).getJSONObject("votes");
                        if (voteJson != null) {
                            vote = new Vote();
                            vote.setDown(voteJson.getInt("down"));
                            vote.setUp(voteJson.getInt("up"));
                        }
                    }
                    joke.setVotes(vote);

                    joke.setCreated_at(jsonArray.getJSONObject(i).getInt("created_at"));
                    joke.setContent(jsonArray.getJSONObject(i).getString("content"));
                    joke.setState(jsonArray.getJSONObject(i).getString("state"));
                    joke.setComments_count(jsonArray.getJSONObject(i).getInt("comments_count"));
                    joke.setAllow_comment(jsonArray.getJSONObject(i).getBoolean("allow_comment"));

                    jokes.add(joke);
                }
            }
            result.setCount(count);
            result.setPage(currentPage);
            result.setTotal(totalCount);
            result.setItems(jokes);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}

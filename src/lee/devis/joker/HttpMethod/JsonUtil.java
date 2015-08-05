package lee.devis.joker.HttpMethod;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	
	private static final String SPACE = "";


	protected int getJsonIntegerResult(JSONObject jsonObject, String key) {
		int jsonIntResult = -1;
		try {
			jsonIntResult = jsonObject.has(key) ? jsonObject.getInt(key) : -1;
		} catch (JSONException e) {
			e.printStackTrace();
			jsonIntResult = -1;
		} finally {
			
		}
		return jsonIntResult;
	}

	protected String getJsonStringResult(JSONObject jsonObject, String key) {
		String jsonStringResult = SPACE;
		try {
			jsonStringResult = jsonObject.has(key) ? jsonObject.getString(key) : SPACE;
		} catch (JSONException e) {
			e.printStackTrace();
			jsonStringResult = SPACE;
		} finally {
			
		}
		return jsonStringResult;
	}

	protected double getJsonDoubleResult(JSONObject jsonObject, String key) {
		double jsonDoubleResult = 0;
		try {
			jsonDoubleResult = jsonObject.has(key) ? jsonObject.getDouble(key) : 0;
		} catch (JSONException e) {
			e.printStackTrace();
			jsonDoubleResult = 0;
		} finally {
			
		}
		return jsonDoubleResult;
	}

	protected List<String> getJsonStringListResult(JSONObject jsonObject, String key) {
		List<String> list = null;
		try {
			JSONArray jsonArray = jsonObject.has(key) ? jsonObject.getJSONArray(key) : null;
			if (jsonArray != null && jsonArray.length() > 0) {
				list = new ArrayList<String>();
				for (int i = 0; i < jsonArray.length(); i++) {
					list.add(jsonArray.getString(i));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			 list = null;
		} finally {
			
		}
		return list;
	}

	protected JSONArray getJsonArrayResult(JSONObject jsonObject, String key) {
		JSONArray jsonArray = null;
		try {
			jsonArray = jsonObject.has(key) ? jsonObject.getJSONArray(key) : null;
		} catch (JSONException e) {
			e.printStackTrace();
			jsonArray = null;
		} finally {
			
		}
		return jsonArray;
	}

	protected JSONObject getJsonObjectResult(JSONObject jsonObject, String key) {
		JSONObject jsonResult = null;
		try {
			jsonResult = jsonObject.has(key) ? jsonObject.getJSONObject(key) : null;
		} catch (JSONException e) {
			e.printStackTrace();
			jsonResult = null;
		} finally {
			
		}
		return jsonResult;
	}

	protected boolean getJsonBooleanResult(JSONObject jsonObject, String key) {
		boolean jsonBooleanResult = false;
		try {
			jsonBooleanResult = jsonObject.has(key) ? jsonObject.getBoolean(key) : false;
		} catch (JSONException e) {
			e.printStackTrace();
			jsonBooleanResult = false;
		} finally {
			
		}
		return jsonBooleanResult;
	}
}

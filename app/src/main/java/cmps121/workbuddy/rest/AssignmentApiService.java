package cmps121.workbuddy.rest;

import cmps121.workbuddy.model.Assignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface AssignmentApiService {

    @GET("/api/v1/courses/7171/assignments")
    Call<List<Assignment>> getAssignments(@Query("access_token") String access_token);

}

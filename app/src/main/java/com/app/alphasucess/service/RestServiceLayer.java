package com.app.alphasucess.service;

import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.data.model.StateResponse;
import com.app.alphasucess.ui.data.model.SubjectTopicData;
import com.app.alphasucess.ui.data.model.SubscriptionData;
import com.app.alphasucess.ui.data.model.SubscriptionDetailData;
import com.app.alphasucess.ui.data.model.SubscriptionListData;
import com.app.alphasucess.ui.data.model.TopicModelData;
import com.app.alphasucess.ui.data.model.VerifyOTP;
import com.app.alphasucess.ui.tabui.adapter.CommentData;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadData;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookData;
import com.app.alphasucess.ui.tabui.home.adapter.HomeData;
import com.app.alphasucess.ui.tabui.login.LoginResponse;
import com.app.alphasucess.ui.tabui.signup.adapters.ProfileDetailData;
import com.app.alphasucess.ui.tabui.test.adapters.AllTestData;
import com.app.alphasucess.ui.tabui.test.adapters.SingleTestQuestion;
import com.app.alphasucess.ui.tabui.test.adapters.TestResultData;
import com.google.gson.JsonObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RestServiceLayer
{
    @POST("/api/App/oauth/login")
    @FormUrlEncoded
    Call<LoginResponse> loginService(@Field("UserName")String uName, @Field("Password")String password, @Field("grant_type")String grant_type,@Field("DeviceID") String deviceID,@Field("DeviceType") String deviceType);

    @POST("/api/App/oauth/login")
    @FormUrlEncoded
    Call<LoginResponse> refreshToken(@Field("refresh_token")String refresh_token, @Field("grant_type")String grant_type);


    @POST("/api/App/ForgotPassword")
    @FormUrlEncoded
    Call<ResoureData> forgotPassword(@Field("Phone")String uName);

    @POST("/api/App/UpdateProfile")
    @FormUrlEncoded
    Call<ResoureData> updateProfile(@Field("Address")String address,@Field("Email")String email,@Field("Name")String name,@Field("StatesID")int StatesID,@Field("Password")String password);

    @POST("/api/App/oauth/VerifyPhone")
    @FormUrlEncoded
    Call<VerifyOTP> verifyOtp(@Field("Phone")String uPhone, @Field("OTP")String uOTP);

    @POST("/api/App/UserRegister")
    @FormUrlEncoded
    Call<JsonObject> signUpApi(@Field("Email")String uEmail,@Field("Name")String uName,@Field("Password")String uPass,
                               @Field("Phone")String uPhone,@Field("StateID") String uStateId,@Field("Address")String uAddress,
                               @Field("isReffered") boolean isRefer,@Field("ReferredBy") String uReferredBy,@Field("DeviceID")String deviceID,
                               @Field("DeviceType")String deviceType);

    @POST("api/App/StatesList")
    Call<StateResponse> stateListData();

    @POST("api/App/UserProfile")
    Call<ResoureData<ProfileDetailData>> getProfileDetails();

    @POST
    Call<JsonObject> resourceData(@Url String url);

    @POST("api/App/BooksList")
    @FormUrlEncoded
    Call<ResoureData<List<EbookData>>> ebookListData(@Header ("Authorization") String authorization, @Field("page_number")String pageNumber);

    @POST("/api/App/PDFList")
    @FormUrlEncoded
    Call<ResoureData<List<DownloadData>>> pdfListData(@Header ("Authorization") String authorization,@Field("page_number")String pageNumber,@Field("category_id")String categoryId,@Field("subject_id")String subjectId);

    @POST
    @FormUrlEncoded
    Call<ResoureData> bookLikeOrUnLike(@Url String url,@Header ("Authorization") String authorization,@Field("ID")String userId);

    @POST("/api/App/CommentList")
    @FormUrlEncoded
    Call<ResoureData<List<CommentData>>> commentDataList(@Header ("Authorization") String authorization,@Field("id")String id,@Field("page_number")String pageNumber);

    @POST("/api/App//AddCommentOnPDF")
    @FormUrlEncoded
    Call<ResoureData> addCommentData(@Header ("Authorization") String authorization,@Field("exampdfid")String exampdfid,@Field("comment")String comment);

    @POST("api/App/TestList")
    @FormUrlEncoded
    Call<ResoureData<List<AllTestData>>> testListData(@Header ("Authorization") String authorization,@Field("exam_id")String examId, @Field("page_number")String pageNumber);

    @POST("/api/App/SingleTestQuestions")
    @FormUrlEncoded
    Call<ResoureData<SingleTestQuestion>> singleTestQuestions(@Header ("Authorization") String authorization, @Field("ID")String Id);

    @POST("/api/App/VideoList")
    @FormUrlEncoded
    Call<ResoureData<List<LiveData>>> liveDataList(@Header ("Authorization") String authorization,@Field("exam_id")String examId, @Field("page_number")String pageNumber);

    @POST("/api/App/SingleVideo")
    @FormUrlEncoded
    Call<ResoureData<LiveData>> singleVideodetails(@Header ("Authorization") String authorization, @Field("id")String Id);

    @POST("/api/App/HomeScreen")
    Call<ResoureData<HomeData>> homeScreenDataList(@Header ("Authorization") String authorization);

    @POST("/api/App/ExamCategory")
    Call<ResoureData<List<ExamData>>> examCategoryList(@Header ("Authorization") String authorization);

    @POST("/api/App/SubscriptionList")
    Call<ResoureData<List<SubscriptionData>>> subscriptionList(@Header ("Authorization") String authorization);

    @POST("api/App/CategorySubscriptions")
    @FormUrlEncoded
    Call<ResoureData<List<SubscriptionData>>> subscriptionDataCategory(@Header("Authorization")String authorization,@Field("ID")String id);

    @POST("/api/App/SingleSubscription")
    @FormUrlEncoded
    Call<ResoureData<SubscriptionData>> singleSubscriptionData(@Header("Authorization")String authorization,@Field("ID")String id);

    @POST("/api/App/SingleSubscription")
    @FormUrlEncoded
    Call<ResoureData<SubscriptionDetailData>> subscriptionDetailData(@Header("Authorization")String authorization,@Field("ID")String id);

    @POST("/api/App/SubscriptionSubjects")
    @FormUrlEncoded
    Call<ResoureData<List<SubscriptionListData>>> subscriptionDetailList(@Header("Authorization")String authorization, @Field("ID")String id);

    @POST("/api/App/SubjectTopics")
    @FormUrlEncoded
    Call<ResoureData<List<SubjectTopicData>>> subscriptionSubjectTopicList(@Header("Authorization")String authorization, @Field("ID")String id);

    @POST("/api/App/SubmitResults")
    @FormUrlEncoded
    Call<ResoureData<TestResultData>> testResultOrLeaderboardData(@Header("Authorization")String authorization, @Field("TestID")String id, @Field("Marks") String marks, @Field("TestResult") String testResult);

    @POST("/api/App/TopicData")
    @FormUrlEncoded
   Call<ResoureData<TopicModelData>> topicDataDetail(@Header("Authorization")String authorization, @Field("ID")String id);
}

public interface ReservationApi {
    @POST("Reservations")
    Call<ResponseBody> postReservation(@Body Reservation reservation);
    @GET("api/Reservations")
    Call<List<Reservation>> getReservations();

    @PUT("api/Reservations/{id}")
    Call<Reservation> updateReservation(@Path("id") int id, @Body Reservation reservation);
}
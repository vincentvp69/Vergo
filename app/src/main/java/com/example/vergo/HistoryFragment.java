public class HistoryFragment extends Fragment {
    private ListView historyListView;
    private ArrayAdapter<String> historyAdapter;
    private List<Reservation> reservationList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        historyListView = rootView.findViewById(R.id.historyListView);
        fetchReservations();
        return rootView;
    }

    private void fetchReservations() {
        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://web.socem.plymouth.ac.uk/COMP2000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReservationApiService apiService = retrofit.create(ReservationApiService.class);

        // Make the API Call
        apiService.getReservations().enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                if (response.isSuccessful()) {
                    reservationList = response.body();
                    updateListView();
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void updateListView() {
        List<String> historicalEntries = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            // Convert Reservation object to string for ArrayAdapter
            // Example: historicalEntries.add(reservation.toString());
        }
        historyAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, historicalEntries);
        historyListView.setAdapter(historyAdapter);
    }
}

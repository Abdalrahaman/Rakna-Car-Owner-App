package com.example.rakna.raknagraduationproject.View.hassan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rakna.raknagraduationproject.Model.hassanModel.IndividualLocation;
import com.example.rakna.raknagraduationproject.Model.hassanModel.util.LinearLayoutManagerWithSmoothScroller;
import com.example.rakna.raknagraduationproject.Model.hassanModel.util.StringConstants;
import com.example.rakna.raknagraduationproject.R;
import com.example.rakna.raknagraduationproject.View.AbdoView.LockConnectionActivity;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.MapboxDirections;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.building.BuildingPlugin;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import com.mapbox.turf.TurfConstants;
import com.mapbox.turf.TurfConversion;
import com.trafi.anchorbottomsheetbehavior.AnchorBottomSheetBehavior;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mapbox.core.constants.Constants.PRECISION_6;
import static com.mapbox.mapboxsdk.style.expressions.Expression.eq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.expressions.Expression.neq;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineWidth;

/**
 * Activity with a Mapbox map and recyclerview to view various locations
 */
public class MapsActivity extends AppCompatActivity implements
        MapboxMap.OnMapClickListener, PermissionsListener {


    private static final LatLngBounds LOCKED_MAP_CAMERA_BOUNDS = new LatLngBounds.Builder()
            .include(new LatLng(40.87096725853152, -74.08277394720501))
            .include(new LatLng(40.67035340371385,
                    -73.87063900287112)).build();

    private static final int MAPBOX_LOGO_OPACITY = 75;
    private static final int CAMERA_MOVEMENT_SPEED_IN_MILSECS = 1200;
    private static final float NAVIGATION_LINE_WIDTH = 9;
    private static final float BUILDING_EXTRUSION_OPACITY = .8f;
    private static final String PROPERTY_SELECTED = "selected";
    private static final String BUILDING_EXTRUSION_COLOR = "#c4dbed";
    private DirectionsRoute currentRoute;
    private FeatureCollection featureCollection;
    private MapboxMap mapboxMap;
    private MapView mapView;
    private RecyclerView locationsRecyclerView;
    private ArrayList<IndividualLocation> listOfIndividualLocations;
    private CustomThemeManager customThemeManager;
    private LocationRecyclerViewAdapter styleRvAdapter;
    private int chosenTheme;
    private PermissionsManager permissionsManager;
    private LocationComponent locationComponent;
    NavigationMapRoute navigationMapRoute;
    private String TAG = "MapsActivity";
    private static LatLng MOCK_DEVICE_LOCATION_LAT_LNG;
    Point mockCurrentLocation, selectedFeaturePoint;
    private CardView card_Item_info_Location;
    private AnchorBottomSheetBehavior mAnchorBottomSheetBehavior;
    private Button reserveGarageButton, shareGarageButton, arriveGarageButton;
    private Button reserveGarageBottomSheet, callGarageBottomSheet, shareGarageBottomSheet;
    private TextView carOwnerNameTextview, carOwnerRateTextview;
    private TextView garageNameTextview, garageRateTextview, garageaddressTextview, garageStateTextview;
    private TextView garageNameBottomSheet, garageRateBottomSheet, garageAddressBottomSheet, garageStateBottomSheet, garagePhoneBottomSheet;
    private FloatingActionButton Fab_Search;
    private int REQUEST_CODE_AUTOCOMPLETE=11;
    GeoJsonSource source;
    Marker marker;

    private String ownerId;

    String singleLocationPhoneNum;

    private String selectedGarageId = "";

    @SuppressWarnings( {"MissingPermission"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configure the Mapbox access token. Configuration can either be called in your application
        // class or in the same activity which contains the mapview.
        Mapbox.getInstance(this, getString(R.string.access_token));

        // Hide the status bar for the map to fill the entire screen
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inflate the layout with the the MapView. Always inflate this after the Mapbox access token is configured.
        setContentView(R.layout.activity_maps);

        initWidgets();

        savedInstanceState = getIntent().getExtras();
        ownerId = savedInstanceState.getString("id");
        showCarOwnerInfo(savedInstanceState.getString("name"), savedInstanceState.getString("rate"));

        actionWidgets();

//        Card_Item_Location_layout= LayoutInflater.from(this).inflate(R.layout.single_location_map_view_rv_card,null);
////        Card_Item_Location=Card_Item_Location_layout.findViewById(R.id.map_view_location_card);
//        Card_Item_Location_layout.setVisibility(View.GONE);

        cardVisibility(0);

        // Create a GeoJSON feature collection from the GeoJSON file in the assets folder.
        try {
            getFeatureCollectionFromJson();
        } catch (Exception exception) {
            Log.e("MapsActivity", "onCreate: " + exception);
            Toast.makeText(this, R.string.failure_to_load_file, Toast.LENGTH_LONG).show();
        }

        // Initialize a list of IndividualLocation objects for future use with recyclerview
        listOfIndividualLocations = new ArrayList<>();

        // Initialize the theme that was selected in the previous activity. The blue theme is set as the backup default.
//        chosenTheme = getIntent().getIntExtra(StringConstants.SELECTED_THEME, R.style.AppTheme_Blue);
        chosenTheme = R.style.AppTheme_Neutral;

        // bottom sheet
        View bottomSheet = findViewById(R.id.bottom_sheet);
        mAnchorBottomSheetBehavior = AnchorBottomSheetBehavior.from(bottomSheet);

        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {

                // Initialize the custom class that handles marker icon creation and map styling based on the selected theme
                customThemeManager = new CustomThemeManager(chosenTheme, MapsActivity.this);

                mapboxMap.setStyle(new Style.Builder().fromUrl(customThemeManager.getMapStyle()), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Setting the returned mapboxMap object (directly above) equal to the "globally declared" one
                        MapsActivity.this.mapboxMap = mapboxMap;

                        // Adjust the opacity of the Mapbox logo in the lower left hand corner of the map
                        ImageView logo = mapView.findViewById(R.id.logoView);
                        logo.setAlpha(MAPBOX_LOGO_OPACITY);

                        // Set bounds for the map camera so that the user can't pan the map outside of the NYC area
//                        mapboxMap.setLatLngBoundsForCameraTarget(LOCKED_MAP_CAMERA_BOUNDS);

                        // Set up the SymbolLayer which will
                        // show the icons for each store location
                        enableLocationComponent(style);

                        new JsonUtils().execute();
                    }

                });

            }
        });

    }


    public void initWidgets(){

        carOwnerNameTextview = findViewById(R.id.tv_owner_name);
        carOwnerRateTextview = findViewById(R.id.tv_owner_rate);

        reserveGarageButton = findViewById(R.id.btn_reserve);
        shareGarageButton = findViewById(R.id.btn_share);
        arriveGarageButton = findViewById(R.id.btn_arrived);

        reserveGarageBottomSheet = findViewById(R.id.bs_garage_reserve);
        callGarageBottomSheet = findViewById(R.id.bs_garage_call);
        shareGarageBottomSheet = findViewById(R.id.bs_garage_share);

        card_Item_info_Location = findViewById(R.id.garage_info_loca_card);

        garageNameTextview = findViewById(R.id.tv_garage_name);
        garageRateTextview = findViewById(R.id.tv_garage_rate);
        garageaddressTextview = findViewById(R.id.tv_garage_address);
        garageStateTextview = findViewById(R.id.tv_garage_state);

        garageNameBottomSheet = findViewById(R.id.bs_garage_name);
        garageRateBottomSheet = findViewById(R.id.bs_garage_rate);
        garageAddressBottomSheet = findViewById(R.id.bs_garage_service);
        garageStateBottomSheet = findViewById(R.id.bs_garage_status);
        garagePhoneBottomSheet = findViewById(R.id.bs_garage_phone);

        // Set up the Mapbox map
        mapView = findViewById(R.id.mapView);
        Fab_Search=findViewById(R.id.fab);
    }


    public void actionWidgets(){

        Fab_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new PlaceAutocomplete.IntentBuilder()
                        .accessToken(getString(R.string.access_token))
                        .build(MapsActivity.this);
                startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
            }
        });


        card_Item_info_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandedBottomSheetBehavior();
            }
        });

        reserveGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemClick(selectedFeaturePoint);
                arriveGarageButton.setVisibility(View.VISIBLE);
            }
        });

        shareGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MapsActivity.this, "share don't coding ?!", Toast.LENGTH_SHORT).show();

            }
        });

        arriveGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, LockConnectionActivity.class);
                intent.putExtra("ownerId",ownerId);
                intent.putExtra("garageId", selectedGarageId);
                intent.putExtra("isReserved",true);
                startActivity(intent);

                arriveGarageButton.setVisibility(View.INVISIBLE);
            }
        });


        reserveGarageBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CollapsedBottomSheetBehavior();
                onItemClick(selectedFeaturePoint);
            }
        });

        callGarageBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + singleLocationPhoneNum));
                startActivity(intent);
            }
        });

        shareGarageBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MapsActivity.this, "share don't coding ?!", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            // Activate the MapboxMap LocationComponent to show user location
            // Adding in LocationComponentOptions is also an optional parameter
            locationComponent = mapboxMap.getLocationComponent();
            locationComponent.activateLocationComponent(this, loadedMapStyle);
            locationComponent.setLocationComponentEnabled(true);


            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            Toast.makeText(this, ""+ locationComponent.getLastKnownLocation().getLongitude()+"-"+
                    locationComponent.getLastKnownLocation().getLatitude(), Toast.LENGTH_SHORT).show();
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }


    private void showBuildingExtrusions() {
        // Use the Mapbox building plugin to display and customize the opacity/color of building extrusions
        BuildingPlugin buildingPlugin = new BuildingPlugin(mapView, mapboxMap, mapboxMap.getStyle());
        buildingPlugin.setVisibility(true);
        buildingPlugin.setOpacity(BUILDING_EXTRUSION_OPACITY);
        buildingPlugin.setColor(Color.parseColor(BUILDING_EXTRUSION_COLOR));
    }

    @Override
    public boolean onMapClick(@NonNull LatLng point) {

        handleClickIcon(mapboxMap.getProjection().toScreenLocation(point));
        return true;
    }
    @SuppressWarnings( {"MissingPermission"})
    private boolean handleClickIcon(PointF screenPoint) {

        MOCK_DEVICE_LOCATION_LAT_LNG = new LatLng(locationComponent.getLastKnownLocation().getLongitude(),locationComponent.getLastKnownLocation().getLatitude());


        List<Feature> features = mapboxMap.queryRenderedFeatures(screenPoint, "store-location-layer-id");
        if (!features.isEmpty()) {
            String name = features.get(0).getStringProperty("name");
            List<Feature> featureList = featureCollection.features();
            for (int i = 0; i < featureList.size(); i++) {

                if (featureList.get(i).getStringProperty("name").equals(name)) {
                    selectedFeaturePoint = (Point) featureList.get(i).geometry();

                    if (featureSelectStatus(i)) {
                        setFeatureSelectState(featureList.get(i), false);
                    } else {
                        setSelected(i);
                    }
                    if (selectedFeaturePoint.latitude() != MOCK_DEVICE_LOCATION_LAT_LNG.getLatitude()) {
                        for (int x = 0; x < featureCollection.features().size(); x++) {

                            if (listOfIndividualLocations.get(x).getLocation().getLatitude() == selectedFeaturePoint.latitude()) {
                                // Scroll the recyclerview to the selected marker's card. It's "x-1" below because
                                // the mock device location marker is part of the marker list but doesn't have its own card
                                // in the actual recyclerview.

                                // get garage id to reserve in it
                                selectedGarageId = listOfIndividualLocations.get(x).getGarageId();

                                // show garage data in card map
                                updateGarageInfoCard(listOfIndividualLocations.get(x).getName(),
                                        listOfIndividualLocations.get(x).getRate(),
                                        listOfIndividualLocations.get(x).getAddress(),
                                        listOfIndividualLocations.get(x).getStatus()+" Now");

                                // show garage data in bottom sheet layout
                                updateGarageInfoBottomSheet(listOfIndividualLocations.get(x).getName(),
                                        listOfIndividualLocations.get(x).getRate(),
                                        listOfIndividualLocations.get(x).getAddress(),
                                        listOfIndividualLocations.get(x).getPhoneNum(),
                                        listOfIndividualLocations.get(x).getStatus()+" Now");

                                cardVisibility(1);

                                break;
                            }
                        }
                    }
                } else {
                    setFeatureSelectState(featureList.get(i), false);
                }
            }


            return true;
        } else {
            return false;
        }

    }

    public void cardVisibility(int flag){

        if (flag == 0){
            card_Item_info_Location.setVisibility(View.GONE);
        }else{
            card_Item_info_Location.setVisibility(View.VISIBLE);
        }
    }

    public void showCarOwnerInfo(String carOwnerName, String carOwnerRate){

        carOwnerNameTextview.setText(carOwnerName);
        carOwnerRateTextview.setText(carOwnerRate);
    }

    public void updateGarageInfoCard(String garageName, String garageRate,
                                     String garageAddress, String garageState){

        garageNameTextview.setText(garageName);
        garageRateTextview.setText(garageRate);
        garageaddressTextview.setText(garageAddress);
        if ("Empty Now".equals(garageState)){
            garageStateTextview.setTextColor(Color.parseColor("#126115"));
            reserveGarageButton.setEnabled(true);
        }else{
            garageStateTextview.setTextColor(Color.parseColor("#C20303"));
            reserveGarageButton.setEnabled(false);
        }
        garageStateTextview.setText(garageState);

        arriveGarageButton.setVisibility(View.INVISIBLE);
    }

    public void updateGarageInfoBottomSheet(String garageName, String garageRate,
                                     String garageAddress, String garagePhone, String garageState){

        garageNameBottomSheet.setText(garageName);
        garageRateBottomSheet.setText(garageRate);
        garageAddressBottomSheet.setText(garageAddress);
        garagePhoneBottomSheet.setText("+20 " + garagePhone);
        if ("Empty Now".equals(garageState)){
            garageStateBottomSheet.setTextColor(Color.parseColor("#126115"));
        }else{
            garageStateBottomSheet.setTextColor(Color.parseColor("#C20303"));
        }
        garageStateBottomSheet.setText(garageState);

    }

    public void expandedBottomSheetBehavior() {
        // Expanding the bottom sheet
        mAnchorBottomSheetBehavior.setState(AnchorBottomSheetBehavior.STATE_EXPANDED);
    }

    public void CollapsedBottomSheetBehavior() {
        // Expanding the bottom sheet
        mAnchorBottomSheetBehavior.setState(AnchorBottomSheetBehavior.STATE_COLLAPSED);
    }

//    // Method to share either text or URL.
//    private void shareTextUrl() {
//        Intent share = new Intent(android.content.Intent.ACTION_SEND);
//        share.setType("text/plain");
//        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//
//        // Add data to the intent, the receiving app will decide
//        // what to do with it. http://maps.google.com/?q=35.17380831799959,-86.1328125
//        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
//        share.putExtra(Intent.EXTRA_TEXT, "http://www.codeofaninja.com");
//
//        startActivity(Intent.createChooser(share, "Share link!"));
//    }

    /**
     * The LocationRecyclerViewAdapter's interface which listens to clicks on each location's card
     *
     * @param selectedPoint the clicked card's position/index in the overall list of cards
     */
    @SuppressWarnings( {"MissingPermission"})

    public void onItemClick(Point selectedPoint) {
        // Get the selected individual location via its card's position in the recyclerview of cards
//        IndividualLocation selectedLocation = listOfIndividualLocations.get(position);

        // Evaluate each Feature's "select state" to appropriately style the location's icon
//        List<Feature> featureList = featureCollection.features();
//        Point selectedLocationPoint = (Point) featureCollection.features().get(position).geometry();
//        for (int i = 0; i < featureList.size(); i++) {
//            if (featureList.get(i).getStringProperty("name").equals(selectedLocation.getName())) {
//                if (featureSelectStatus(i)) {
//                    setFeatureSelectState(featureList.get(i), false);
//                } else {
//                    setSelected(i);
//                }
//            } else {
//                setFeatureSelectState(featureList.get(i), false);
//            }
//        }



        // Reposition the map camera target to the selected marker
        if (selectedPoint != null) {
            repositionMapCamera(selectedPoint);
        }

        // Check for an internet connection before making the call to Mapbox Directions API
        if (deviceHasInternetConnection()) {
            // Start call to the Mapbox Directions API

                MOCK_DEVICE_LOCATION_LAT_LNG = new LatLng(locationComponent.getLastKnownLocation().getLongitude(),locationComponent.getLastKnownLocation().getLatitude());

                // Set up origin and destination coordinates for the call to the Mapbox Directions API
                 mockCurrentLocation = Point.fromLngLat(MOCK_DEVICE_LOCATION_LAT_LNG.getLatitude(),
                         MOCK_DEVICE_LOCATION_LAT_LNG.getLongitude());

                 Toast.makeText(this, ""+ mockCurrentLocation, Toast.LENGTH_SHORT).show();

                getRoute(mockCurrentLocation,selectedPoint);
//                getInformationFromDirectionsApi(selectedLocationPoint, true, null);
        } else {
            Toast.makeText(this, R.string.no_internet_message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Adds a SymbolLayer which will show all of the location's icons
     */
    private void initStoreLocationIconSymbolLayer() {
        Style style = mapboxMap.getStyle();
        if (style != null) {
            // Add the icon image to the map
            style.addImage("store-location-icon-id", customThemeManager.getUnselectedMarkerIcon());

            // Create and add the GeoJsonSource to the map
            GeoJsonSource storeLocationGeoJsonSource = new GeoJsonSource("store-location-source-id");
            style.addSource(storeLocationGeoJsonSource);

            // Create and add the store location icon SymbolLayer to the map
            SymbolLayer storeLocationSymbolLayer = new SymbolLayer("store-location-layer-id",
                    "store-location-source-id");
            storeLocationSymbolLayer.withProperties(
                    iconImage("store-location-icon-id"),
                    iconAllowOverlap(true),
                    iconIgnorePlacement(true)
            );
            style.addLayer(storeLocationSymbolLayer);

        } else {
            Log.d("StoreFinderActivity", "initStoreLocationIconSymbolLayer: Style isn't ready yet.");

            throw new IllegalStateException("Style isn't ready yet.");
        }
    }

    /**
     * Adds a SymbolLayer which will show the selected location's icon
     */
    private void initSelectedStoreSymbolLayer() {
        Style style = mapboxMap.getStyle();
        if (style != null) {

            // Add the icon image to the map
            style.addImage("selected-store-location-icon-id", customThemeManager.getSelectedMarkerIcon());

            // Create and add the store location icon SymbolLayer to the map
            SymbolLayer selectedStoreLocationSymbolLayer = new SymbolLayer("selected-store-location-layer-id",
                    "store-location-source-id");
            selectedStoreLocationSymbolLayer.withProperties(
                    iconImage("selected-store-location-icon-id"),
                    iconAllowOverlap(true)
            );
            selectedStoreLocationSymbolLayer.withFilter(eq((get(PROPERTY_SELECTED)), literal(true)));
            style.addLayer(selectedStoreLocationSymbolLayer);
        } else {
            Log.d("StoreFinderActivity", "initSelectedStoreSymbolLayer: Style isn't ready yet.");
            throw new IllegalStateException("Style isn't ready yet.");
        }
    }

    /**
     * Checks whether a Feature's boolean "selected" property is true or false
     *
     * @param index the specific Feature's index position in the FeatureCollection's list of Features.
     * @return true if "selected" is true. False if the boolean property is false.https://hassan-elkhadrawy.000webhostapp.com/list_of_locations.geojson
     */
    private boolean featureSelectStatus(int index) {
        if (featureCollection == null) {
            return false;
        }
        return featureCollection.features().get(index).getBooleanProperty(PROPERTY_SELECTED);
    }

    /**
     * Set a feature selected state.
     *
     * @param index the index of selected feature
     */
    private void setSelected(int index) {
        Feature feature = featureCollection.features().get(index);
        setFeatureSelectState(feature, true);
        refreshSource();
    }

    /**
     * Selects the state of a feature
     *
     * @param feature the feature to be selected.
     */
    private void setFeatureSelectState(Feature feature, boolean selectedState) {
        feature.properties().addProperty(PROPERTY_SELECTED, selectedState);
        refreshSource();
    }


    /**
     * Updates the display of data on the map after the FeatureCollection has been modified
     */
    private void refreshSource() {
         source = mapboxMap.getStyle().getSourceAs("store-location-source-id");
        if (source != null && featureCollection != null) {
            source.setGeoJson(featureCollection);
        }
    }

    private void getInformationFromDirectionsApi(Point destinationPoint,
                                                 final boolean fromMarkerClick, @Nullable final Integer listIndex) {
        // Set up origin and destination coordinates for the call to the Mapbox Directions API
        Point mockCurrentLocation = Point.fromLngLat(MOCK_DEVICE_LOCATION_LAT_LNG.getLongitude(),
                MOCK_DEVICE_LOCATION_LAT_LNG.getLatitude());

        Point destinationMarker = Point.fromLngLat(destinationPoint.longitude(), destinationPoint.latitude());

        // Initialize the directionsApiClient object for eventually drawing a navigation route on the map
        MapboxDirections directionsApiClient = MapboxDirections.builder()
                .origin(mockCurrentLocation)
                .destination(destinationMarker)
                .overview(DirectionsCriteria.OVERVIEW_FULL)
                .profile(DirectionsCriteria.PROFILE_DRIVING)
                .accessToken(getString(R.string.access_token))
                .build();

        directionsApiClient.enqueueCall(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                // Check that the response isn't null and that the response has a route
                if (response.body() == null) {
                    Log.e("MapsActivity", "No routes found, make sure you set the right user and access token.");
                } else if (response.body().routes().size() < 1) {
                    Log.e("MapsActivity", "No routes found");
                } else {
                    if (fromMarkerClick) {
                        // Retrieve and draw the navigation route on the map
                        currentRoute = response.body().routes().get(0);
                        drawNavigationPolylineRoute(currentRoute);
                    } else {
                        // Use Mapbox Turf helper method to convert meters to miles and then format the mileage number
                        DecimalFormat df = new DecimalFormat("#.#");
                        String finalConvertedFormattedDistance = String.valueOf(df.format(TurfConversion.convertLength(
                                response.body().routes().get(0).distance(), TurfConstants.UNIT_METERS,
                                TurfConstants.UNIT_MILES)));

                        // Set the distance for each location object in the list of locations
                        if (listIndex != null) {
                            listOfIndividualLocations.get(listIndex).setDistance(finalConvertedFormattedDistance);
                            // Refresh the displayed recyclerview when the location's distance is set
                            styleRvAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {
                Toast.makeText(MapsActivity.this, R.string.failure_to_retrieve, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void getRoute(Point origin, Point destination) {
        NavigationRoute.builder(this)
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        // You can get the generic HTTP info about the response
                        Log.d(TAG, "Response code: " + response.code());
                        if (response.body() == null) {
                            Log.e(TAG, "No routes found, make sure you set the right user and access token.");
                            return;
                        } else if (response.body().routes().size() < 1) {
                            Log.e(TAG, "No routes found");
                            return;
                        }

                        currentRoute = response.body().routes().get(0);

                        // Draw the route on the map
                        if (navigationMapRoute != null) {
                            navigationMapRoute.removeRoute();
                        } else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, mapboxMap, R.style.NavigationMapRoute);
                        }
                        navigationMapRoute.addRoute(currentRoute);
                        boolean simulateRoute = true;
                        NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                                .directionsRoute(currentRoute)
                                .shouldSimulateRoute(simulateRoute)
                                .build();
                        // Call this method with Context from within an Activity
                        NavigationLauncher.startNavigation(MapsActivity.this, options);

                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage());
                    }
                });
    }








    private void repositionMapCamera(Point newTarget) {
        CameraPosition newCameraPosition = new CameraPosition.Builder()
                .target(new LatLng(newTarget.latitude(), newTarget.longitude()))
                .build();
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition), CAMERA_MOVEMENT_SPEED_IN_MILSECS);
    }
    @SuppressWarnings( {"MissingPermission"})
    private void addMockDeviceLocationMarkerToMap() {
        // Add the fake user location marker to the map
        Style style = mapboxMap.getStyle();
        if (style != null) {
            // Add the icon image to the map
            style.addImage("mock-device-location-icon-id", customThemeManager.getMockLocationIcon());

            // Point originPoint = Point.fromLngLat();
            style.addSource(new GeoJsonSource("mock-device-location-source-id", Feature.fromGeometry(
                    Point.fromLngLat(MOCK_DEVICE_LOCATION_LAT_LNG.getLongitude(), MOCK_DEVICE_LOCATION_LAT_LNG.getLatitude()))));

            style.addLayer(new SymbolLayer("mock-device-location-layer-id",
                    "mock-device-location-source-id").withProperties(
                    iconImage("mock-device-location-icon-id"),
                    iconAllowOverlap(true),
                    iconIgnorePlacement(true)
            ));
        } else {
            throw new IllegalStateException("Style isn't ready yet.");
        }
    }

    private void getFeatureCollectionFromJson() throws IOException {
        try {
            // Use fromJson() method to convert the GeoJSON file into a usable FeatureCollection object
            featureCollection = FeatureCollection.fromJson(loadGeoJsonFromAsset("list_of_locations.geojson"));

        } catch (Exception exception) {
            Log.e("MapsActivity", "getFeatureCollectionFromJson: " + exception);
        }
    }

    private String loadGeoJsonFromAsset(String filename) {
        try {
            // Load the GeoJSON file from the local asset folder
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (Exception exception) {
            Log.e("MapsActivity", "Exception Loading GeoJSON: " + exception.toString());
            exception.printStackTrace();
            return null;
        }
    }

//    private void setUpRecyclerViewOfLocationCards() {
//        // Initialize the recyclerview of location cards and a custom class for automatic card scrolling
//        locationsRecyclerView = findViewById(R.id.map_layout_rv);
//        locationsRecyclerView.setHasFixedSize(true);
//        locationsRecyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(this));
//        styleRvAdapter = new LocationRecyclerViewAdapter(listOfIndividualLocations,
//                getApplicationContext(), this);
//        locationsRecyclerView.setAdapter(styleRvAdapter);
//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(locationsRecyclerView);
//    }

    private void drawNavigationPolylineRoute(DirectionsRoute route) {
        // Retrieve and update the source designated for showing the store location icons
        GeoJsonSource source = mapboxMap.getStyle().getSourceAs("navigation-route-source-id");
        if (source != null) {
            source.setGeoJson(FeatureCollection.fromFeature(Feature.fromGeometry(
                    LineString.fromPolyline(route.geometry(), PRECISION_6))));
        }
    }

    private void initNavigationPolylineLineLayer() {
        // Create and add the GeoJsonSource to the map
        GeoJsonSource navigationLineLayerGeoJsonSource = new GeoJsonSource("navigation-route-source-id");
        mapboxMap.getStyle().addSource(navigationLineLayerGeoJsonSource);

        // Create and add the LineLayer to the map to show the navigation route line
        LineLayer navigationRouteLineLayer = new LineLayer("navigation-route-layer-id",
                navigationLineLayerGeoJsonSource.getId());
        navigationRouteLineLayer.withProperties(
                lineColor(customThemeManager.getNavigationLineColor()),
                lineWidth(NAVIGATION_LINE_WIDTH)
        );
        mapboxMap.getStyle().addLayerBelow(navigationRouteLineLayer, "store-location-layer-id");
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "dcd", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocationComponent(mapboxMap.getStyle());
        } else {
            Toast.makeText(this, "jh", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    // Add the mapView's lifecycle to the activity's lifecycle methods
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    private boolean deviceHasInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    /**
     * Custom class which creates marker icons and colors based on the selected theme
     */
    class CustomThemeManager {
        private int selectedTheme;
        private Context context;
        private Bitmap unselectedMarkerIcon;
        private Bitmap selectedMarkerIcon;
        private Bitmap mockLocationIcon;
        private int navigationLineColor;
        private String mapStyle;

        CustomThemeManager(int selectedTheme, Context context) {
            this.selectedTheme = selectedTheme;
            this.context = context;
            initializeTheme();
        }

        private void initializeTheme() {
            switch (selectedTheme) {
                case R.style.AppTheme_Blue:
                    mapStyle = getString(R.string.blue_map_style);
                    navigationLineColor = getResources().getColor(R.color.navigationRouteLine_blue);
                    unselectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_unselected_ice_cream);
                    selectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_selected_ice_cream);
                    mockLocationIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_user_location);
                    break;
                case R.style.AppTheme_Purple:
                    mapStyle = getString(R.string.purple_map_style);
                    navigationLineColor = getResources().getColor(R.color.navigationRouteLine_purple);
                    unselectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.purple_unselected_burger);
                    selectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.purple_selected_burger);
                    mockLocationIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.purple_user_location);
                    break;
                case R.style.AppTheme_Green:
                    mapStyle = getString(R.string.terminal_map_style);
                    navigationLineColor = getResources().getColor(R.color.navigationRouteLine_green);
                    unselectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.green_unselected_money);
                    selectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.green_selected_money);
                    mockLocationIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.green_user_location);
                    break;
                case R.style.AppTheme_Neutral:
                    mapStyle = Style.MAPBOX_STREETS;
                    navigationLineColor = getResources().getColor(R.color.navigationRouteLine_neutral);
                    unselectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.white_unselected_house);
                    selectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.gray_selected_house);
                    mockLocationIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.neutral_orange_user_location);
                    break;
                case R.style.AppTheme_Gray:
                    mapStyle = Style.LIGHT;
                    navigationLineColor = getResources().getColor(R.color.navigationRouteLine_gray);
                    unselectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.white_unselected_bike);
                    selectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.gray_selected_bike);
                    mockLocationIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.gray_user_location);
                    break;
            }
        }

        public Bitmap getUnselectedMarkerIcon() {
            return unselectedMarkerIcon;
        }

        public Bitmap getMockLocationIcon() {
            return mockLocationIcon;
        }

        public Bitmap getSelectedMarkerIcon() {
            return selectedMarkerIcon;
        }

        int getNavigationLineColor() {
            return navigationLineColor;
        }

        public String getMapStyle() {
            return mapStyle;
        }
    }


    class JsonUtils extends AsyncTask<Void, Void, String> {

        String JsonUrl = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            JsonUrl = "https://rakna-app.000webhostapp.com/show_garage_location.php?driver_licence="+ownerId;

        }

        @Override
        protected String doInBackground(Void... voids) {
            try {

                URL url = new URL(JsonUrl);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {

                    sb.append(line + "\n");

                }
                is.close();
                bufferedReader.close();
                con.disconnect();
                return sb.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            featureCollection = FeatureCollection.fromJson(s);

            // Set up the SymbolLayer which will show the icons for each store location
            initStoreLocationIconSymbolLayer();

            // Set up the SymbolLayer which will show the selected store icon
            initSelectedStoreSymbolLayer();

            // Set up the LineLayer which will show the navigation route line to a particular store location
            initNavigationPolylineLineLayer();

            // Create a list of features from the feature collection
            List<Feature> featureList = featureCollection.features();

            // Retrieve and update the source designated for showing the store location icons
            GeoJsonSource source = mapboxMap.getStyle().getSourceAs("store-location-source-id");
            if (source != null) {
                source.setGeoJson(FeatureCollection.fromFeatures(featureList));
            }

            if (featureList != null) {

                for (int x = 0; x < featureList.size(); x++) {

                    Feature singleLocation = featureList.get(x);

                    // Get the single location's String properties to place in its map marker
                    String singleLocationId = singleLocation.getStringProperty("garage_id");
                    String singleLocationName = singleLocation.getStringProperty("name");
                    String singleLocationStatus = singleLocation.getStringProperty("status");
                    String singleLocationRate = singleLocation.getStringProperty("rate");
                    String singleLocationHours = "1h 20m";
                    String singleLocationDescription ="Sea Street , Cairo Governorate";
                    singleLocationPhoneNum = singleLocation.getStringProperty("phone");


                    // Add a boolean property to use for adjusting the icon of the selected store location
                    singleLocation.addBooleanProperty(PROPERTY_SELECTED, false);

                    // Get the single location's LatLng coordinates
                    Point singleLocationPosition = (Point) singleLocation.geometry();
//                    Toast.makeText(MapsActivity.this, ""+singleLocationPosition.latitude(), Toast.LENGTH_SHORT).show();

                    // Create a new LatLng object with the Position object created above
                    LatLng singleLocationLatLng = new LatLng(singleLocationPosition.latitude(),
                            singleLocationPosition.longitude());

                    // Add the location to the Arraylist of locations for later use in the recyclerview
                    listOfIndividualLocations.add(new IndividualLocation(
                            singleLocationId,
                            singleLocationName,
                            singleLocationDescription,
                            singleLocationHours,
                            singleLocationPhoneNum,
                            singleLocationLatLng,
                            singleLocationStatus,
                            singleLocationRate
                    ));

                    // Call getInformationFromDirectionsApi() to eventually display the location's
                    // distance from mocked device location
//                getInformationFromDirectionsApi(singleLocationPosition, false, x);
                }
                // Add the fake device location marker to the map. In a real use case scenario,
                // the Maps SDK's LocationComponent can be used to easily display and customize
                // the device location's puck


//                setUpRecyclerViewOfLocationCards();

                mapboxMap.addOnMapClickListener(MapsActivity.this);

//                Toast.makeText(MapsActivity.this, "Click on a card", Toast.LENGTH_SHORT).show();

                // Show 3d buildings if the blue theme is being used
                if (customThemeManager.getNavigationLineColor() == R.color.navigationRouteLine_blue) {
                    showBuildingExtrusions();
                }
            }
        }
    }

    @SuppressWarnings( {"MissingPermission"})

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            CarmenFeature feature = PlaceAutocomplete.getPlace(data);
            String PLACE_NAME= PlaceAutocomplete.getPlace(data).placeName();

            Point singleLocationPosition = (Point) feature.geometry();

            Point destinationPoint = Point.fromLngLat(singleLocationPosition.longitude(), singleLocationPosition.latitude());
           Point originPoint = Point.fromLngLat(locationComponent.getLastKnownLocation().getLongitude(),
                    locationComponent.getLastKnownLocation().getLatitude());

            repositionMapCamerasearch(singleLocationPosition,PLACE_NAME);


        }
    }

     void repositionMapCamerasearch(Point newTarget,String P_Name) {

        CameraPosition newCameraPosition = new CameraPosition.Builder()
                .target(new LatLng(newTarget.latitude(), newTarget.longitude()))
                .build();
        if (marker!=null){
            marker.remove();
            marker=mapboxMap.addMarker(new MarkerOptions().setTitle(P_Name).position(new LatLng(newTarget.latitude(),newTarget.longitude())));

        }else {
            marker=mapboxMap.addMarker(new MarkerOptions().setTitle(P_Name).position(new LatLng(newTarget.latitude(),newTarget.longitude())));

        }
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition), 1200);
    }

}
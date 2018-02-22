package com.example.miodragmilosevic.roomtest;

/**
 * Created by miodrag.milosevic on 2/9/2018.
 */

public class EpiDrawerAdapter {
    //public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

//    private static final String TAG = "CustomDrawerAdapter";
//    Context context;
//    List<DrawerItem> drawerItemList;
//    int layoutResID;
//
//    public CustomDrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems) {
//        super(context, layoutResourceID, listItems);
//        this.context = context;
//        this.layoutResID = layoutResourceID;
//        this.drawerItemList = listItems;
//    }
//
//    private static class DrawerItemHolder {
//        TextView itemName, title, profileName, profileEmail;
//        ImageView icon;
//        ProfilePictureView profilePick;
//        LinearLayout headerLayout, itemLayout;
//        RelativeLayout profileLayout;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        DrawerItemHolder drawerHolder;
//        View view = convertView;
//
//        if (view == null) {
//            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//            drawerHolder = new DrawerItemHolder();
//
//            view = inflater.inflate(layoutResID, parent, false);
//
//            drawerHolder.profilePick = (ProfilePictureView) view.findViewById(R.id.userProfilePicture);
//            drawerHolder.profileName = (TextView) view.findViewById(R.id.text_main_name);
//            drawerHolder.profileEmail = (TextView) view.findViewById(R.id.sub_text_email);
//
//            drawerHolder.itemName = (TextView) view.findViewById(R.id.drawer_itemName);
//            drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);
//            drawerHolder.title = (TextView) view.findViewById(R.id.drawerTitle);
//
//            drawerHolder.profileLayout = (RelativeLayout) view.findViewById(R.id.profileLayout);
//            drawerHolder.headerLayout = (LinearLayout) view.findViewById(R.id.headerLayout);
//            drawerHolder.itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);
//
//            view.setTag(drawerHolder);
//
//        } else {
//            drawerHolder = (DrawerItemHolder) view.getTag();
//
//        }
//
//        DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);
//
//        if (dItem.isProfileSection()) {
//            drawerHolder.profileLayout.setVisibility(LinearLayout.VISIBLE);
//            drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
//            drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
//
//            ParseUser currentUser = ParseUser.getCurrentUser();
//
//            try {
//                if (currentUser.has("profile")) {
//                    JSONObject userProfile = currentUser.getJSONObject("profile");
//
//                    if (userProfile.has("facebookId")) {
//                        drawerHolder.profilePick.setProfileId(userProfile.getString("facebookId"));
//                    } else {
//                        drawerHolder.profilePick.setProfileId(null);
//                    }
//
//                    if (userProfile.has("name")) {
//                        drawerHolder.profileName.setText(userProfile.getString("name"));
//                    } else {
//                        drawerHolder.profileName.setText("");
//                    }
//
//                    if (userProfile.has("email")) {
//                        drawerHolder.profileEmail.setText(userProfile.getString("email"));
//                    } else {
//                        drawerHolder.profileEmail.setText("");
//                    }
//
//                }
//
//            } catch (JSONException e) {
//                Log.d(TAG, "Error parsing saved user data.");
//            }
//
//
//
////			List<UserProfileDrawerItem> userList = new ArrayList<UserProfileDrawerItem>();
////			userList.add(new UserProfileDrawerItem(R.drawable.user1, "Ahamed Ishak","ishak@gmail.com"));
////			userList.add(new UserProfileDrawerItem(R.drawable.user2, "Brain Jekob","brain.j@gmail.com"));
////
////			CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(context, R.layout.custom_spinner_item, userList);
////
////			drawerHolder.spinner.setAdapter(adapter);
////			drawerHolder.spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
////
////						@Override
////						public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
////							Toast.makeText(context, "User Changed", Toast.LENGTH_SHORT).show();
////						}
////						@Override
////						public void onNothingSelected(AdapterView<?> arg0) {
////
////						}
////					});
//
//        } else if (dItem.getTitle() != null) {
//
//            drawerHolder.headerLayout.setVisibility(LinearLayout.VISIBLE);
//            drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
//            drawerHolder.profileLayout.setVisibility(LinearLayout.INVISIBLE);
//            drawerHolder.title.setText(dItem.getTitle());
//            Log.d("Getview", "Passed4");
//
//        } else {
//
//            drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
//            drawerHolder.profileLayout.setVisibility(LinearLayout.INVISIBLE);
//            drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);
//            drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));
//            drawerHolder.itemName.setText(dItem.getItemName());
//            Log.d("Getview", "Passed5");
//
//        }
//
//        return view;
//
//    }
//}
    
}

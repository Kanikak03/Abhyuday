package com.example.rajat.abhyuday;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    public ImageView thumbnail;
    private Context mContext;
    private List<Album> albumList;


    public AlbumsAdapter(EventActivity mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + " Participants");


        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(mContext,eventdetails.class);
                intent.putExtra("ename",album.getName());
                intent.putExtra("img",album.getThumbnail());

                //content

                if(album.getName().equals("Coding"))
                {
                    intent.putExtra("desc"," Perfection in code is achieved, not when there is nothing more to add, but when there is nothing more to take away.\n" +
                            "\n" +
                            "SKILLS: C language.\n");
                    intent.putExtra("rules","RULES:\n" +
                            "\n" +
                            "Team Event: 2 members per team.\n" +
                            "Teams qualifying the preliminary round will be selected for further rounds.\n" +
                            "Usage of internet is not allowed.\n" +
                            "Rules will be disclosed on the day of event.\n" +
                            "\n" +
                            "ENTRY FEES: Rs 200\n" +
                            "\n" +
                            "CASH PRIZE:\n" +
                            "1ST PLACE: Rs 2000\n" +
                            "2ND PLACE: Rs 1000");
                    //same way add the fees and the prize money and participation details for all the cases

                }
                else if(album.getName().equals("Pixelmania"))
                {
                    intent.putExtra("desc","“You don't make a photograph just with a camera Explore your imaginations and emotions and express it through your photographs and visuals”\n" +
                            "Requirements: Camera and for video editing [Laptop with video editing software/Mobile Phone video editing Application.");
                    intent.putExtra("rules","RULES:\n" +
                            "\n" +
                            "Team event: 2 members per team.\n" +
                            "The rules will be given by the organizers conducting the event.\n" +
                            "Rules for finals will be disclosed on spot.\n" +
                            "\n" +
                            "ENTRY FEES: Rs 200\n" +
                            "\n" +
                            "CASH PRIZE:\n" +
                            "1ST PLACE: Rs 1500\n" +
                            "2ND PLACE: Rs 750");
                }

                else if(album.getName().equals("Gaming"))
                {
                    intent.putExtra("desc","\"LAN games foster the mindset that allows creativity to grow, prove your skills and tactics here”\n" +
                            "\n" +
                            "COUNTER STRIKE RULES :\n" +
                            "\n" +
                            "Game version: Need For Speed Mostwanted v1.3\n" +
                            "Competition method: 4 in a row\n" +
                            "Match type: Circuit and Sprint.\n");
                    intent.putExtra("rules","COUNTER STRIKE RULES :\n" +
                            "\n" +
                            "Game version: Need For Speed Mostwanted v1.3\n" +
                            "Competition method: 4 in a row\n" +
                            "Match type: Circuit and Sprint.\n" +
                            "\n" +
                            "ENTRY FEES: Rs 400 \n" +
                            "\n" +
                            "CASH PRIZE:\n" +
                            "1ST PLACE Rs 4000\n" +
                            "2ND PLACE Rs 2000");
                }
//                else if(album.getName().equals("Debate"))
//                {
//                    intent.putExtra("desc","type the description of coding");
//                    intent.putExtra("rules","type the rules for coding");
//                }
                else if(album.getName().equals("Web Designing"))
                {
                    intent.putExtra("desc"," Web Developer ? Bored of the routine? Gear up your creative thoughts and add a little funk to the WEB. Come join us in the quest to find the best!\n" +
                            "SKILLS: Basic knowledge of Html, CSS and JavaScript.");
                    intent.putExtra("rules","Team event: 2 members per team.\n" +
                            "Usage of internet is not allowed.\n" +
                            "Rules for finals will be disclosed on spot.\n" +
                            "ENTRY FEES: Rs 200\n" +
                            "\n" +
                            "CASH PRIZE:\n" +
                            "1ST PLACE: Rs 2000\n" +
                            "2ND PLACE: Rs 1000");
                }
                else if(album.getName().equals("Quiz"))
                {
                    intent.putExtra("desc","Let your Knowledge Shine, Let the brain race with every passing of minute. Let the knower awaken in you and drive the knowledge of your mind to the victory.\n" +
                            "SKILLS: Current IT affairs, General knowledge and latest technologies.");
                    intent.putExtra("rules","Team event: 2 members per team.\n" +
                            "Rules will be disclosed on the day of event.\n" +
                            "ENTRY FEES: Rs 200\n" +
                            "\n" +
                            "CASH PRIZE:\n" +
                            "1ST PLACE: Rs 2000\n" +
                            "2ND PLACE: Rs 1000");
                }
                else if(album.getName().equals("Treasure Hunt"))
                {
                    intent.putExtra("desc","“You can either be a victim of the world or an Adventurer in search of treasure. It all depends on how your point of view is and a new form of event where participant’s knowledge skills are also tested along with common sense”.");
                    intent.putExtra("rules","RULES:\n" +
                            "\n" +
                            "Each team must consist of 3 members.\n" +
                            "Clues will be hidden in certain spots in the campus, the boundary of which will be specified just before the treasure hunt begins.\n" +
                            "Rules may be subject to change.\n" +
                            "Organiser’s decision is final.\n" +
                            "\n" +
                            "ENTRY FEES: Rs 300\n" +
                            "\n" +
                            "CASH PRIZE:\n" +
                            "1ST PLACE: Rs 3000");
                }
                else if(album.getName().equals("IT Manager"))
                {
                    intent.putExtra("desc","Innovation is not something that can be purchased.The more energy, creativity and attention you invest in it, the greater the yield. Come, be that Leader who help themselves and others to do the right things. Set a direction, build an inspiring vision, and create some-thing new. Be the perseverance, lead your abilities, acquire knowledge, show your strength, excellence and above all be the team builder.");
                    intent.putExtra("rules","RULES:\n" +
                            "\n" +
                            "Individual event.\n" +
                            "Rounds will be disclosed on the day of event.\n" +
                            "The rules will be given by the organizers conducting the event.\n" +
                            "ENTRY FEES: Rs 100\n" +
                            "\n" +
                            "CASH PRIZE:\n" +
                            "1ST PLACE: Rs 1500\n" +
                            "2ND PLACE: Rs 750");
                }
                else if(album.getName().equals("Ideathon"))
                {
                    intent.putExtra("desc","If you are passionate about your startup idea and looking to grow your business, we invite you students to pitch your IT startup idea. Take this opportunity to pitch your idea, launch your startup and change the world!\n" +
                            "\n" +
                            "EVENT REQUREMENTS :\n" +
                            "\n" +
                            "You may submit your abstract on the day of event or you can submit abstract to the link given below. http://goo.gl/forms/WUBR9iXKoD\n" +
                            "On the day of the event you can pitch with presentation or video\n");
                    intent.putExtra("rules","RULES:\n" +
                            "Team members: 3 Members per team\n" +
                            "Idea Must be Unique and Innovative.\n" +
                            "The final rules will be given by the organizers condu-cting the event\n" +
                            "\n" +
                            "ENTRY FEES: Rs 300\n" +
                            "\n" +
                            "CASH PRIZE:\n" +
                            "1ST PLACE: Rs 2000\n" +
                            "2ND PLACE: Rs 1000");
                }

//                ActivityOptionsCompat options=ActivityOptionsCompat.
//                        makeSceneTransitionAnimation(this,android.view.thumbnail,"event");
                mContext.startActivity(intent);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView overflow, thumbnail;
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            cardView = (CardView) itemView.findViewById(R.id.card_view);

        }
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Register", Toast.LENGTH_SHORT).show();
                    return true;
                //case R.id.action_play_next:
                  //  Toast.makeText(mContext, "Register", Toast.LENGTH_SHORT).show();
                    //return true;
                default:
            }
            return false;
        }
    }
}

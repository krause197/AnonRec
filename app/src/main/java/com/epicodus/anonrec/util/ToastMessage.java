package com.epicodus.anonrec.util;

import java.util.Random;

/**
 * Created by Guest on 12/16/16.
 */
public interface ToastMessage {
    final Random random = new Random();
    String[] toastMessages = new String[] {"Pain is the touchstone of spiritual growth.",
            "Happiness is appreciating what you have, not getting what you want.", "Alcoholic drinking's three stages: impulsive, compulsive, repulsive.",
            "I spent a lifetime in hell and it only took me twelve steps to get to heaven.", "When I came to recovery I realized that being a child for 28 years nearly killed me",
            "Serenity is not the absence of conflict, but the ability to cope with it.", "I am the black sheep of the family. I came to Alcoholics Anonymous and found the rest of the herd.",
            "I don't thank God for opening the gates of Heaven and letting me in, I thank God for opening the gates of Hell and letting me out", "I searched for the enemy that I could not see, when I looked in the mirror the enemy was me.",
            "Spiritual Awakenings often come in Rude Awakenings, so you better pay attention!", "When you dance with a gorilla it is the gorilla who decides when to stop.",
            "Alcohol gave me wings and then slowly took away my sky.", "Walking around drunk is like walking around with your fly open. Everyone sees it except you.",
            "Sereneity is what we get when we quit hoping for a better past.", "An excuse is nothing more than a thin skin stretched over a bald faced lie.",
            "It all works out in the end...if it hasn't worked out yet, it's not the end.", "If I could drink like a regular person...I'd drink all the time. Therein lies our paradox...",
            "My best thinking got me drunk."};
    int randomMsgIndex = random.nextInt(toastMessages.length -1);

    void getToast();
}

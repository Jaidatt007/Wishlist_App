package com.example.wishlistapplication.resources

data class FAQ_Data_Item(val question : String, val answer : String = "None ?")

val faq_Data_Item_List = listOf(
    FAQ_Data_Item(
        question = "What is the Wishlist app?",
        answer = "The Wishlist app helps you organize and keep track of items or experiences you wish to purchase or achieve."
    ),
    FAQ_Data_Item(
        question = "How do I add an item to my wishlist?",
        answer = "To add an item, click on the 'Add' button, fill in the details such as item name, description, and priority, and save it."
    ),
    FAQ_Data_Item(
        question = "Can I edit or delete items in my wishlist?",
        answer = "Yes, you can edit or delete items by selecting them from the list and choosing the respective options."
    ),
    FAQ_Data_Item(
        question = "How can I mark an item as purchased?",
        answer = "You can mark an item as purchased by selecting it and tapping on the 'Mark as Purchased' option."
    ),
    FAQ_Data_Item(
        question = "Can I prioritize items in my wishlist?",
        answer = "Yes, you can set a priority for each item while adding or editing it. Items can be filtered or sorted based on priority."
    ),
    FAQ_Data_Item(
        question = "Is my wishlist data backed up?",
        answer = "Data backup depends on the app's settings. You can enable cloud sync or export your wishlist for backup."
    ),
    FAQ_Data_Item(
        question = "Can I share my wishlist with others?",
        answer = "Yes, you can share your wishlist using the share feature, which generates a link or a file to send to others."
    ),
    FAQ_Data_Item(
        question = "Does the app support notifications or reminders?",
        answer = "Yes, you can set reminders for items, and the app will notify you about them at the scheduled time."
    ),
    FAQ_Data_Item(
        question = "Can I categorize items in my wishlist?",
        answer = "Yes, the app allows you to create categories or tags to organize your wishlist items effectively."
    ),
    FAQ_Data_Item(
        question = "Is the app available offline?",
        answer = "Yes, the Wishlist app works offline. However, features like cloud sync require an internet connection."
    )
)
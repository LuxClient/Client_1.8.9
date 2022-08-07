package net.luxclient.ui.notification;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NotificationHandler {

    //USAGE: LuxClient.getNotificationHandler().sendNotification(new Notification("Test", NotificationType.INFO));

    private List<Notification> livingNotifications;

    public NotificationHandler() {
        this.livingNotifications = new ArrayList<Notification>();
    }

    public void renderNotifications() {
        if(!livingNotifications.isEmpty()) {

            Iterator<Notification> iter = livingNotifications.iterator();
            while (iter.hasNext()) {
                Notification notification = iter.next();
                notification.renderNotification();

                if (!notification.isLiving())
                    iter.remove();
            }

        }
    }

    public void sendNotification(Notification notification) {
        this.livingNotifications.add(notification);
    }

}

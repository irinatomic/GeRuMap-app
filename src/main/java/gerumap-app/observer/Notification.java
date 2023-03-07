package dsw.gerumap.app.observer;

/*  PRINCIP RADA
    Model je publisher a view je subscriber
    MapNode iz naseg modela ce impl. IFPublisher tkd ce ga svaka potklasa impl.takodje

    1. Dogodi se event -> controller poziva publishera iz modela (model ima listu subscribera)
    2. Publisher obavestava svoje subscribere (iz view-a)
    3. Pri prosledjivanju notifikacije uvek se prosledjuje isti radi jednostavnosti koda:
        this, null ili neka nasa klasa Notifikacije
 */

public enum Notification {
    ADD_AUTHOR, RENAME, REMOVE, NEW, REPAINT
}

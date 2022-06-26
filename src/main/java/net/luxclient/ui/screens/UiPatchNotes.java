package net.luxclient.ui.screens;

import net.luxclient.LuxClient;
import net.luxclient.ui.UiScreen;
import net.luxclient.ui.components.buttons.UiButton;
import net.luxclient.ui.components.lists.UiListClear;
import net.luxclient.ui.components.lists.UiListEntry;
import net.luxclient.util.ClientGuiUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UiPatchNotes extends UiScreen {

    private UiNotesList notesList;
    private List<UiListEntry> entries;

    private void createEntries() {
        entries = new ArrayList<>();

        entries.add(new UiHeadingEntry("TEST HEADING"));
        entries.add(new UiNoteEntry("Test lol"));
        entries.add(new UiNoteEntry("another test"));
        entries.add(new PlaceholderEntry());

        entries.add(new UiHeadingEntry("TEST HEADING"));
        entries.add(new UiNoteEntry("Test lol"));
        entries.add(new UiNoteEntry("another test"));
        entries.add(new PlaceholderEntry());

        entries.add(new UiHeadingEntry("TEST HEADING"));
        entries.add(new UiNoteEntry("Test lol"));
        entries.add(new UiNoteEntry("another test"));
        entries.add(new PlaceholderEntry());

        entries.add(new UiHeadingEntry("TEST HEADING"));
        entries.add(new UiNoteEntry("Test lol"));
        entries.add(new UiNoteEntry("another test"));
        entries.add(new PlaceholderEntry());

        entries.add(new UiHeadingEntry("TEST HEADING"));
        entries.add(new UiNoteEntry("Test lol"));
        entries.add(new UiNoteEntry("another test"));
        entries.add(new PlaceholderEntry());

        entries.add(new UiHeadingEntry("TEST HEADING"));
        entries.add(new UiNoteEntry("Test lol"));
        entries.add(new UiNoteEntry("another test"));
        entries.add(new PlaceholderEntry());

        entries.add(new UiHeadingEntry("TEST HEADING"));
        entries.add(new UiNoteEntry("Test lol"));
        entries.add(new UiNoteEntry("another test"));
        entries.add(new PlaceholderEntry());

        entries.add(new UiHeadingEntry("TEST HEADING"));
        entries.add(new UiNoteEntry("Test lol"));
        entries.add(new UiNoteEntry("another test"));
        entries.add(new PlaceholderEntry());

        entries.add(new UiHeadingEntry("TEST HEADING"));
        entries.add(new UiNoteEntry("Test lol"));
        entries.add(new UiNoteEntry("another test"));
        entries.add(new PlaceholderEntry());

        entries.add(new UiHeadingEntry("TEST HEADING"));
        entries.add(new UiNoteEntry("Test lol"));
        entries.add(new UiNoteEntry("another test"));
        entries.add(new PlaceholderEntry());

        entries.add(new UiHeadingEntry("TEST HEADING"));
        entries.add(new UiNoteEntry("Test lol"));
        entries.add(new UiNoteEntry("another test"));
    }

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        this.notesList.drawScreen(mouseX, mouseY, 0);
    }

    @Override
    public void initComponents() {
        this.createEntries();
        this.notesList = new UiNotesList(this.width / 2 - 150, 0, this.width / 2 + 150, this.height, 8, entries);
    }

    @Override
    public void buttonClicked(UiButton button) {

    }

    private static class UiNotesList extends UiListClear {

        public UiNotesList(int x, int y, int width, int height, int entryHeight, List<UiListEntry> entries) {
            super(x, y, width, height, entryHeight, entries);
        }

    }

    private static class UiNoteEntry extends UiListEntry {

        protected String note;

        public UiNoteEntry(String note) {
            this.note = note;
        }

        @Override
        public void renderEntry(int x, int y, int width, int height, int index) {
            LuxClient.Fonts.text.drawString("- " + this.note, x + 5, y + 5, ClientGuiUtils.brandingIconColor.getRGB());
        }

    }

    private static class UiHeadingEntry extends UiListEntry {

        protected String heading;

        public UiHeadingEntry(String heading) {
            this.heading = heading.toUpperCase();
        }

        @Override
        public void renderEntry(int x, int y, int width, int height, int index) {
            LuxClient.Fonts.titleBold.drawStringScaled(this.heading,  x, y, ClientGuiUtils.brandingIconColor.getRGB(), 0.65F);
        }

    }

    private static class PlaceholderEntry extends UiListEntry {

        @Override
        public void renderEntry(int x, int y, int width, int height, int index) {}

    }

}

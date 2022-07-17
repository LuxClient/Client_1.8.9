package net.luxclient.ui.screens;

import net.luxclient.LuxClient;
import net.luxclient.ui.UiScreen;
import net.luxclient.ui.components.buttons.UiButton;
import net.luxclient.ui.components.buttons.UiImageButtonClear;
import net.luxclient.ui.components.lists.UiListClear;
import net.luxclient.ui.components.lists.UiListEntry;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.util.ResourceLocation;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class UiPatchNotes extends UiScreen {

    private UiListClear notesList;
    private List<UiListEntry> entries;

    private void createEntries() {
        entries = new ArrayList<>();

        entries.add(new UiHeadingEntry("NO CHANGES"));
        entries.add(new UiNoteEntry("All updates in the client will show up here"));
    }

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        ClientGuiUtils.drawRoundedRect(this.width / 2 - 110, 35, 220, this.height - 70, 3, ClientGuiUtils.brandingBackgroundColor);
        ClientGuiUtils.drawRoundedRect(this.width / 2 - 105, 60, 210, this.height - 100, 3, ClientGuiUtils.brandingSecondBackgroundColor);
        LuxClient.Fonts.titleBold.drawCenteredTextScaled("PATCH NOTES", this.width / 2, 41, ClientGuiUtils.brandingIconColor.getRGB(), 0.66F);
    }

    @Override
    public void initComponents() {
        this.componentList.add(new UiImageButtonClear(0, this.width / 2 + 90, 41, new ResourceLocation("lux/icons/close.png"), 0, 0, ""));


        this.createEntries();
        this.notesList = new UiListClear(this.width / 2 - 100, 63, 200, this.height - 105, 10, entries);
        this.componentList.add(notesList);
    }

    @Override
    public void buttonClicked(UiButton button) {
        if (button.getId() == 0) {
            this.mc.displayGuiScreen(parenScreen);
        }
        if (button.getId() == 1) {
            String url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (URISyntaxException | IOException e) {
                System.err.println("Unable to open link: " + url);
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.notesList.keyTyped(keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void handleMouseInput() throws IOException {
        this.notesList.handleMouseInput();
        super.handleMouseInput();
    }

    private static class UiNoteEntry extends UiListEntry {

        protected String note;

        public UiNoteEntry(String note) {
            this.note = note;
        }

        @Override
        public void renderEntry(int x, int y, int width, int height, int index) {
            LuxClient.Fonts.text.drawString("- " + this.note, x + 6, y + 3, ClientGuiUtils.brandingIconColor.getRGB());
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

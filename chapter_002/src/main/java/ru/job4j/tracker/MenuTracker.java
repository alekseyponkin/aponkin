package ru.job4j.tracker;

/**
 * Class MenuTracker.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 26.09.2017
 */

/**
 * Inner class EditItem.
 */
class EditItem extends BaseAction {
    /**
     * Constructor class EditItem.
     * @param key menu.
     * @param name menu.
     */
     EditItem(int key, String name) {
        super(key, name);
    }
    /**
     * Execution action.
     * @param input - input data.
     * @param tracker - tracker.
     */
    @Override
    public void execut(Input input, Tracker tracker) {
        String idItem = input.ask("Please enter ID item for change:");
        Item item = new Item(input.ask("Please enter new Name items"), input.ask("Please enter new Description items"));
        item.setIdItem(idItem);
        tracker.update(item);
    }
}

/**
 * Main class MenuTracker.
 */
public class MenuTracker {
    /**
     * Input data.
     */
    private Input input;
    /**
     * Tracker.
     */
    private Tracker tracker;
    /**
     * Array user action.
     */
    private UserAction[] userActions = new UserAction[6];

    /**
     * Constructor class MenuTracker.
     * @param input - input data.
     * @param tracker - tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Add action menu.
     */
    public void fillActions() {
        this.userActions[0] = new MenuTracker.AddItem(0, "Add new item");
        this.userActions[1] = this.new ShowAll(1, "Show all items");
        this.userActions[2] = new EditItem(2, "Edit item");
        this.userActions[3] = new MenuTracker.DeleteItem(3, "Delete item");
        this.userActions[4] = new FindIdItem(4, "Find item by id");
        this.userActions[5] = new FindNameItem(5, "Find items by name");
    }

    /**
     * @param key - select key menu.
     */
    public void select(int key) {
        this.userActions[key].execut(this.input, this.tracker);
    }

    /**
     * Create array key menu.
     * @return array key menu.
     */
    public int[] rangeMenu() {
        int[] range = new int[this.userActions.length];
        for (int i = 0; i < this.userActions.length; i++) {
            range[i] = userActions[i].key();
        }
        return range;
    }

    /**
     * Show menu.
     */
    public void show() {
        System.out.println("+---------------------------+");
        System.out.println("|                           |");
        System.out.println("|          Tracker          |");
        System.out.println("|                           |");
        System.out.println("+---------------------------+");
        for (UserAction action : this.userActions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
        System.out.println("+---------------------------+");
    }

    /**
     * Method outputting array item.
     * @param items item for adding.
     */
    private void showItems(Item[] items) {
        System.out.println("+---------------+---------------+---------------------------+------------------------------------------+");
        System.out.println("|       ID      |      DATE     |           NAME            |               DESCRIPTION                |");
        System.out.println("+---------------+---------------+---------------------------+------------------------------------------+");
        for (Item item : items) {
            System.out.println(String.format("| %s | %s | %s | %s", item.getIdItem(), item.getDateItem(), item.getNameItem(), item.getDescriptionItem()));
            System.out.println("+---------------+---------------+---------------------------+------------------------------------------+");
        }
    }

    /**
     * Inner static class AddItem.
     */
    private static class AddItem extends BaseAction {
        /**
         * Constructor class AddItem.
         * @param key menu.
         * @param name menu.
         */
        AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * Execution action.
         * @param input - input data.
         * @param tracker - tracker.
         */
        @Override
        public void execut(Input input, Tracker tracker) {
            String name = input.ask("Please enter name item:");
            String description = input.ask("Please enter description item:");
            tracker.add(new Item(name, description));
        }
    }

    /**
     * Inner class ShowAll.
     */
    private class ShowAll  extends BaseAction {
        /**
         * Constructor class ShowAll.
         * @param key menu.
         * @param name menu.
         */
        ShowAll(int key, String name) {
            super(key, name);
        }

        /**
         * Execution action.
         * @param input - input data.
         * @param tracker - tracker.
         */
        @Override
        public void execut(Input input, Tracker tracker) {
            showItems(tracker.findAll());
        }
    }

    /**
     * Inner static class DeleteItem.
     */
    private static class DeleteItem  extends BaseAction {
        /**
         * Constructor class DeleteItem.
         * @param key menu.
         * @param name menu.
         */
        DeleteItem(int key, String name) {
            super(key, name);
        }

        /**
         * Execution action.
         * @param input - input data.
         * @param tracker - tracker.
         */
        @Override
        public void execut(Input input, Tracker tracker) {
            tracker.delete(tracker.findById(input.ask("Please enter ID item:")));
        }
    }

    /**
     * Inner class FindIdItem.
     */
    private class FindIdItem  extends BaseAction {
        /**
         * Constructor class FindIdItem.
         * @param key menu.
         * @param name menu.
         */
        FindIdItem(int key, String name) {
            super(key, name);
        }

        /**
         * Execution action.
         * @param input - input data.
         * @param tracker - tracker.
         */
        @Override
        public void execut(Input input, Tracker tracker) {
            showItems(new Item[] {tracker.findById(input.ask("Please enter ID item:"))});
        }
    }

    /**
     * Inner class FindNameItem.
     */
    private class FindNameItem  extends BaseAction {
        /**
         * Constructor class FindNameItem.
         * @param key menu.
         * @param name menu.
         */
        FindNameItem(int key, String name) {
            super(key, name);
        }

        /**
         * Execution action.
         * @param input - input data.
         * @param tracker - tracker.
         */
        @Override
        public void execut(Input input, Tracker tracker) {
            showItems(tracker.findByName(input.ask("Please enter Name item:")));
        }
    }
}

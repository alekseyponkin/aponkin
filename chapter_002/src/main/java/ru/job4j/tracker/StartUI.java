package ru.job4j.tracker;

/**
 * Class StartUI - start program Tracker.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 19.09.2017
 */
public class StartUI {
    /**
     * Input data.
     */
    private Input input;
    /**
     * Tracker.
     */
    private Tracker tracker;
    /**
     * Flag work program Tacker.
     */
    private Boolean work = true;
    /**
     * Constant ADD.
     */
    private static final String ADD = "0";
    /**
     * Constant SHOW.
     */
    private static final String SHOW = "1";
    /**
     * Constant EDIT.
     */
    private static final String EDIT = "2";
    /**
     * Constant DELETE.
     */
    private static final String DELETE = "3";
    /**
     * Constant FIND_ID.
     */
    private static final String FIND_ID = "4";
    /**
     * Constant FIND_NAME.
     */
    private static final String FIND_NAME = "5";
    /**
     * Constant EXIT.
     */
    private static final String EXIT = "6";

    /**
     * Constructor class StartUI.
     * @param input enter data.
     * @param tracker tracker.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method outputting main menu.
     */
    private void showMenu() {
        System.out.println("+---------------------------+");
        System.out.println("|                           |");
        System.out.println("|          Tracker          |");
        System.out.println("|                           |");
        System.out.println("+---------------------------+");
        System.out.println("| 0.Add new item            |");
        System.out.println("| 1.Show all items          |");
        System.out.println("| 2.Edit item               |");
        System.out.println("| 3.Delete item             |");
        System.out.println("| 4.Find item by id         |");
        System.out.println("| 5.Find items by name      |");
        System.out.println("| 6.Exit Program            |");
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
     * Initialising program.
     */
    public void init() {
        while (this.work) {
            this.showMenu();
            switch (input.ask("Please select action:")) {
                case ADD :
                    this.addItem(this.input, this.tracker);
                    break;
                case SHOW :
                    this.showAll(this.tracker);
                    break;
                case EDIT :
                    this.editItem(this.input, this.tracker);
                    break;
                case DELETE :
                    this.deleteItem(this.input, this.tracker);
                    break;
                case FIND_ID :
                    this.findIdItem(this.input, this.tracker);
                    break;
                case FIND_NAME :
                    this.findNameItem(this.input, this.tracker);
                    break;
                case EXIT :
                    work = false;
                    break;
                default:
            }
        }
    }

    /**
     * Method main menu Add new item.
     * @param input input stream.
     * @param tracker tracker.
     */

    private void addItem(Input input, Tracker tracker) {
        String name = input.ask("Please enter name item:");
        String description = input.ask("Please enter description item:");
        tracker.add(new Item(name, description));
    }

    /**Method main menu Show all items.
     * @param tracker tracker.
     */
    private void showAll(Tracker tracker) {
        this.showItems(tracker.findAll());
    }

    /**Method main menu Edit item.
     * @param input input stream.
     * @param tracker tracker.
     */
    private void editItem(Input input, Tracker tracker) {
        String idItem = input.ask("Please enter ID item for change:");
        Item item = new Item(input.ask("Please enter new Name items"), input.ask("Please enter new Description items"));
        item.setIdItem(idItem);
        tracker.update(item);
    }

    /**Method main menu Delete item.
     * @param input input stream.
     * @param tracker tracker.
     */
    private void deleteItem(Input input, Tracker tracker) {
        tracker.delete(tracker.findById(input.ask("Please enter ID item:")));
    }
    /**Method main menu Find item by Id.
     * @param input input stream.
     * @param tracker tracker.
     */
    private void findIdItem(Input input, Tracker tracker) {
        this.showItems(new Item[] {tracker.findById(input.ask("Please enter ID item:"))});
    }

    /**Method main menu Find items by name.
     * @param input input stream.
     * @param tracker tracker.
     */
    private void findNameItem(Input input, Tracker tracker) {
        this.showItems(tracker.findByName(input.ask("Please enter Name item:")));
    }

    /**
     * Method main.
     * @param args args.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}

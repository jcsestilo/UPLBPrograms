from settings import *
import pygame
import pygame_gui
from pygame_gui.core.utility import create_resource_path
from pygame_gui.windows import UIFileDialog
from pygame_gui.elements.ui_text_entry_box import UITextEntryBox




class GUI_WINDOW:
    def __init__(self):
        pygame.init()
        self.screen = pygame.display.set_mode((WIDTH, HEIGHT))
        pygame.display.set_caption(title)
        clock = pygame.time.Clock()


        # MGA EWAN SA UI
        self.manager = pygame_gui.UIManager((WIDTH, HEIGHT), 'themes.json')
        self.time_delta = clock.tick(60)/1000.0
        self.load_button = pygame_gui.elements.UIButton(relative_rect=pygame.Rect((0, 0), (WIDTH * 1/3, 30)), text='(None)', manager=self.manager, object_id='solution_button')
        self.text_box = pygame_gui.elements.UITextEntryBox(relative_rect=pygame.Rect(0, 30, WIDTH * 1/3, 500), object_id='extract_words',
                                     manager=self.manager) 
        self.execute_button = pygame_gui.elements.UIButton(relative_rect=pygame.Rect((0, 528), (WIDTH, 30)), text='EXECUTE', manager=self.manager, object_id='solution_button')
        self.console_box = pygame_gui.elements.UITextBox('',
                             relative_rect=pygame.Rect((0, 550), (WIDTH, 220)),
                             manager=self.manager)

        self.console_input = pygame_gui.elements.UITextEntryLine(relative_rect=pygame.Rect(0, HEIGHT-30, WIDTH, 30), object_id='console_inpt',
                                     manager=self.manager)

        self.console_input.rebuild()       
              
        self.execute_button.rebuild()
        self.text_box.rebuild()


        self.extract_text = ""
        self.file_dialog = None

    def new(self):
        return 
    def draw(self):
        self.screen.fill(BGCOLOUR)
        self.manager.draw_ui(self.screen)

        pygame.display.flip()
    
    def run(self):
        self.running = True
        while self.running:
            self.events()
            self.update()
            self.draw()

    def update(self):
        self.manager.update(self.time_delta)


    def events(self):
        for event in pygame.event.get():

            # handles close 

            if event.type == pygame.QUIT:
                pygame.quit()
                quit(0)

            if (event.type == pygame_gui.UI_BUTTON_PRESSED and event.ui_element == self.load_button):
                    self.file_dialog = UIFileDialog(pygame.Rect(160, 50, 440, 500),
                                                    self.manager,
                                                    window_title='Load File...',
                                                    initial_file_path='./',
                                                    allow_picking_directories=True,
                                                    allow_existing_files_only=True)
                    self.load_button.disable()

            if (event.type == pygame_gui.UI_BUTTON_PRESSED and event.ui_element == self.execute_button):
                self.console_box.set_text(self.extract_text)
                # self.console_box.rebuild()

            if (event.type == pygame_gui.UI_WINDOW_CLOSE
                    and event.ui_element == self.file_dialog):
                self.load_button.enable()

            if event.type == pygame_gui.UI_FILE_DIALOG_PATH_PICKED:
                try:
                    file_path = create_resource_path(event.text)
                    self.load_button.hide()
                    self.load_button.remove()
                    self.load_button = pygame_gui.elements.UIButton(relative_rect=pygame.Rect((0, 0), (WIDTH * 1/3, 30)), text=file_path, manager=self.manager, object_id='load_button')
                    # print(file_path)
                    print(self.extract_text)

                except pygame.error:
                    pass

            if event.type == pygame_gui.UI_TEXT_ENTRY_CHANGED and event.ui_element == self.text_box:
                self.extract_text=event.text

            self.manager.process_events(event)

            
    

gui = GUI_WINDOW()

while True:
    gui.new()
    gui.run()   
import pygame

COLOR_WHITE = (255, 255, 255) # screen color and 0 cell
COLOR_PEACH = (255, 229, 180) # non-0 cell color
COLOR_MINTGREEN = (62, 180, 137)
COLOR_BLACK = (0, 0, 0) # font color

class Button(pygame.sprite.Sprite):
    def __init__(self, x, y, w, h, font, text):
        super().__init__() 
        text_surf = font.render(text, True, (0, 0, 0))
        self.button_image = pygame.Surface((w, h))
        self.button_image.fill(COLOR_PEACH)
        self.button_image.blit(text_surf, text_surf.get_rect(center = (w // 2, h // 2)))
        self.hover_image = pygame.Surface((w, h))
        self.hover_image.fill(COLOR_MINTGREEN)
        self.hover_image.blit(text_surf, text_surf.get_rect(center = (w // 2, h // 2)))
        pygame.draw.rect(self.hover_image, (96, 196, 96), self.hover_image.get_rect(), 3)
        self.image = self.button_image
        self.rect = pygame.Rect(x, y, w, h)

    def update(self):
        hover = self.rect.collidepoint(pygame.mouse.get_pos())
        self.image = self.hover_image if hover else self.button_image
        # for event in event_list:
        #     if event.type == pygame.MOUSEBUTTONDOWN:
        #         if hover and event.button == 1:
        #             print("Solution is clicked")
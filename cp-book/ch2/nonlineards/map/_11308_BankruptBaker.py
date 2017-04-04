
# Problem name: 11308 Bankrupt Baker
# Problem url: https://uva.onlinejudge.org/external/113/11308.pdf
# Author: Andrey Yemelyanov

import sys
import math

def readline():
    return sys.stdin.readline().strip()

def main():
    n_binders = int(readline())
    for i in range(n_binders):
        binder_name = readline()
        m, n, b = [int(x) for x in readline().split()]
        affordable_recipes = []
        ingredient_price = dict()
        for j in range(m):
            line = readline().split()
            ingredient_price[line[0]] = int(line[1])
        for k in range(n):
            recipe_name = readline()
            n_ingredients = int(readline())
            recipe = []
            for l in range(n_ingredients):
                line = readline().split()
                recipe.append((line[0], int(line[1])))
            recipe_cost = get_recipe_cost(recipe, ingredient_price)
            if recipe_cost <= b:
                affordable_recipes.append((recipe_name, recipe_cost))
        print(binder_name.upper())
        if(len(affordable_recipes) == 0):
            print("Too expensive!")
        else:
            print("\n".join(x[0] for x in sorted(sorted(affordable_recipes, key = lambda pair : pair[0]), key = lambda pair : pair[1])))
        print()

def get_recipe_cost(recipe, ingredient_price):
    cost = 0
    for ingredient in recipe:
        cost += ingredient_price[ingredient[0]] * ingredient[1]
    return cost

if __name__=="__main__":
    main()

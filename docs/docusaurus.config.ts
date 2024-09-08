import { themes as prismThemes } from 'prism-react-renderer';
import type { Config } from '@docusaurus/types';
import type * as Preset from '@docusaurus/preset-classic';

const config: Config = {
  title: 'Circumflex',
  tagline: 'A Fabric/Quilt library mod',
  favicon: 'img/favicon.ico',

  url: 'https://diacritics-owo.github.io/',
  baseUrl: '/circumflex/',

  organizationName: 'diacritics-owo',
  projectName: 'circumflex',

  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',

  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },

  presets: [
    [
      'classic',
      {
        docs: {
          sidebarPath: './sidebars.ts',
          editUrl:
            'https://github.com/diacritics-owo/circumflex/tree/main/docs/',
          routeBasePath: '/',
        },
      } satisfies Preset.Options,
    ],
  ],

  themeConfig: {
    navbar: {
      title: 'Circumflex',
      logo: {
        alt: 'circumflex logo',
        src: 'img/logo.svg',
      },
      items: [
        {
          type: 'docSidebar',
          sidebarId: 'documentation',
          position: 'left',
          label: 'Documentation',
        },
        {
          href: 'https://github.com/diacritics-owo/circumflex',
          label: 'GitHub',
          position: 'right',
        },
      ],
    },
    footer: {
      links: [
        {
          label: 'GitHub',
          href: 'https://github.com/diacritics-owo/circumflex',
        },
      ],
      copyright: `Copyright © ${new Date().getFullYear()} diacritics-owo`,
    },
    prism: {
      additionalLanguages: ['java', 'toml', 'groovy'],
      theme: prismThemes.github,
      darkTheme: prismThemes.dracula,
    },
  } satisfies Preset.ThemeConfig,
};

export default config;
